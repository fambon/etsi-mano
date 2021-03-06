/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.node.nfvo.NsdNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsUow extends AbstractNsUnitOfWork {

	private static final Logger LOG = LoggerFactory.getLogger(NsUow.class);

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsdTask nsdTask;

	private final VnfInstantiate instantiateRequest;

	private final transient NsInstanceControllerService nsInstanceControllerService;

	private final transient VnfInstanceLcm nsLcmOpOccsService;

	public NsUow(final NsdTask _task, final VnfInstantiate req, final NsInstanceControllerService _nsInstanceControllerService, final VnfInstanceLcm _nsLcmOpOccsService) {
		super(_task);
		nsdTask = _task;
		instantiateRequest = req;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
	}

	@Override
	public String exec(final NsParameters params) {
		final VnfBlueprint lcm = nsLcmOpOccsService.instantiate(nsdTask.getNsInstanceId(), instantiateRequest);
		final VnfBlueprint result = waitLcmCompletion(lcm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

	@Override
	public String rollback(final NsParameters params) {
		final VnfBlueprint lcm = nsLcmOpOccsService.terminate(nsdTask.getNsInstanceId(), null, 0);
		final VnfBlueprint result = waitLcmCompletion(lcm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

	@Override
	protected String getPrefix() {
		return "nsd";
	}

	private VnfBlueprint waitLcmCompletion(final VnfBlueprint lcm) {
		VnfBlueprint tmp = lcm;
		OperationStatusType state = tmp.getOperationStatus();
		while ((state == OperationStatusType.PROCESSING) || (OperationStatusType.STARTING == state)) {
			tmp = nsLcmOpOccsService.vnfLcmOpOccsGet(lcm.getId());
			state = tmp.getOperationStatus();
			sleepSeconds(1);
		}
		LOG.info("VNF Lcm complete with state: {}", state);
		return tmp;
	}

	private static void sleepSeconds(final long seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (final InterruptedException e) {
			LOG.warn("Interrupted exception.", e);
			Thread.currentThread().interrupt();
		}

	}

	@Override
	public List<WfDependency> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(NsdNode.class, nsdTask.getToscaName(), nsdTask.getId()));
	}

}
