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
package com.ubiqube.etsi.mano.service.pkg.tosca.vnf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.service.pkg.bean.InstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.ProviderData;
import com.ubiqube.etsi.mano.service.pkg.bean.VduInitialDelta;
import com.ubiqube.etsi.mano.service.pkg.bean.VduInstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.VduScalingAspectDeltas;
import com.ubiqube.etsi.mano.service.pkg.tosca.AbstractPackageProvider;
import com.ubiqube.etsi.mano.service.pkg.vnf.VnfPackageProvider;
import com.ubiqube.parser.tosca.api.ArtefactInformations;

import ma.glasnost.orika.MapperFactory;
import tosca.datatypes.nfv.L3ProtocolData;
import tosca.datatypes.nfv.VirtualLinkProtocolData;
import tosca.nodes.nfv.VNF;
import tosca.nodes.nfv.VduCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;
import tosca.nodes.nfv.vdu.VirtualBlockStorage;
import tosca.nodes.nfv.vdu.VirtualObjectStorage;
import tosca.policies.nfv.ScalingAspects;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaVnfPackageProvider extends AbstractPackageProvider implements VnfPackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaVnfPackageProvider.class);

	public ToscaVnfPackageProvider(final byte[] data) {
		super(data);
	}

	@Override
	protected void additionalMapping(final MapperFactory mapperFactory) {
		mapperFactory.classMap(ProviderData.class, VNF.class)
				.field("vnfProvider", "provider")
				.field("vnfProductName", "productName")
				.field("vnfSoftwareVersion", "softwareVersion")
				.field("vnfdVersion", "descriptorVersion")
				.field("descriptorVersion", "descriptorVersion")
				.field("vnfdId", "descriptorId")
				.field("descriptorId", "descriptorId")
				.field("flavorId", "flavourId")
				.byDefault()
				.register();
		mapperFactory.classMap(ArtefactInformations.class, AdditionalArtifact.class)
				.field("path", "artifactPath")
				.field("checksum", "checksum.hash")
				.field("algorithm", "checksum.algorithm")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualBlockStorage.class, VnfStorage.class)
				.field("swImageData", "softwareImage")
				.field("virtualBlockStorageData.sizeOfStorage", "size")
				.field("internalName", "toscaName")
				.field("", "myField:{|setType('BLOCK')}")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualObjectStorage.class, VnfStorage.class)
				.field("virtualObjectStorageData.maxSizeOfStorage", "size")
				.field("internalName", "toscaName")
				.field("", "myField:{|setType('OBJECT')}")
				.byDefault()
				.register();
		mapperFactory.classMap(Compute.class, VnfCompute.class)
				.field("swImageData", "softwareImage")
				.field("internalName", "toscaName")
				.field("virtualStorageReq", "storages")
				.field("virtualCompute.virtualCpu.numVirtualCpu", "numVcpu")
				.field("virtualCompute.virtualCpu.cpuArchitecture", "cpuArchitecture")
				.field("virtualCompute.virtualMemory.virtualMemSize", "virtualMemorySize")
				.field("virtualCompute.virtualLocalStorage[0].sizeOfStorage", "diskSize")
				.byDefault()
				.register();
		mapperFactory.classMap(VduCp.class, VnfLinkPort.class)
				.field("virtualBindingReq", "virtualBinding")
				.field("virtualLinkReq", "virtualLink")
				.field("order", "interfaceOrder")
				.field("internalName", "toscaName")
				.byDefault()
				.register();
		mapperFactory.classMap(VnfVirtualLink.class, VnfVl.class)
				.field("internalName", "toscaName")
				.field("vlProfile", "vlProfileEntity")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualLinkProtocolData.class, VlProtocolData.class)
				.field("l3ProtocolData.ipAllocationPools", "ipAllocationPools")
				.byDefault()
				.register();
		mapperFactory.classMap(L3ProtocolData.class, L3Data.class)
				.field("name", "l3Name")
				.byDefault()
				.register();
		mapperFactory.classMap(tosca.nodes.nfv.VnfExtCp.class, VnfExtCp.class)
				.field("externalVirtualLinkReq", "externalVirtualLink")
				.field("internalVirtualLinkReq", "internalVirtualLink")
				.field("internalName", "toscaName")
				.byDefault()
				.register();
	}

	@Override
	public ProviderData getProviderPadata() {
		final List<ProviderData> vnfs = getListOf(VNF.class, ProviderData.class, new HashMap<>());
		if (vnfs.isEmpty()) {
			LOG.warn("No VNF node found in the package.");
			return new ProviderData();
		}
		return vnfs.get(0);
	}

	@Override
	public Set<AdditionalArtifact> getAdditionalArtefacts(final Map<String, String> parameters) {
		return getCsarFiles(AdditionalArtifact.class);
	}

	@Override
	public Set<VnfCompute> getVnfComputeNodes(final Map<String, String> parameters) {
		return this.getSetOf(Compute.class, VnfCompute.class, parameters);
	}

	@Override
	public Set<VnfStorage> getVnfStorages(final Map<String, String> parameters) {
		return getSetOf(VnfStorage.class, parameters, VirtualBlockStorage.class, VirtualObjectStorage.class);
	}

	@Override
	public Set<VnfVl> getVnfVirtualLinks(final Map<String, String> parameters) {
		return this.getSetOf(VnfVirtualLink.class, VnfVl.class, parameters);
	}

	@Override
	public Set<VnfLinkPort> getVnfVduCp(final Map<String, String> parameters) {
		return this.getSetOf(VduCp.class, VnfLinkPort.class, parameters);
	}

	@Override
	public Set<VnfExtCp> getVnfExtCp(final Map<String, String> parameters) {
		return this.getSetOf(tosca.nodes.nfv.VnfExtCp.class, VnfExtCp.class, parameters);
	}

	@Override
	public Set<ScalingAspect> getScalingAspects(final Map<String, String> parameters) {
		final List<ScalingAspects> list = getObjects(ScalingAspects.class, parameters);
		final Set<ScalingAspect> ret = new HashSet<>();
		for (final ScalingAspects scalingAspects : list) {
			final Map<String, tosca.datatypes.nfv.ScalingAspect> sa = scalingAspects.getAspects();
			final Set<ScalingAspect> tmp = sa.entrySet().stream().map(x -> {
				final ScalingAspect scaleRet = getMapper().map(x.getValue(), ScalingAspect.class);
				scaleRet.setName(x.getKey());
				return scaleRet;
			}).collect(Collectors.toSet());
			ret.addAll(tmp);
		}
		return ret;
	}

	@Override
	public List<com.ubiqube.etsi.mano.service.pkg.bean.InstantiationLevels> getInstatiationLevels(final Map<String, String> parameters) {
		return getListOf(InstantiationLevels.class, com.ubiqube.etsi.mano.service.pkg.bean.InstantiationLevels.class, parameters);
	}

	@Override
	public List<com.ubiqube.etsi.mano.service.pkg.bean.VduInstantiationLevels> getVduInstantiationLevels(final Map<String, String> parameters) {
		return getListOf(VduInstantiationLevels.class, com.ubiqube.etsi.mano.service.pkg.bean.VduInstantiationLevels.class, parameters);
	}

	@Override
	public List<com.ubiqube.etsi.mano.service.pkg.bean.VduInitialDelta> getVduInitialDelta(final Map<String, String> parameters) {
		return getListOf(VduInitialDelta.class, com.ubiqube.etsi.mano.service.pkg.bean.VduInitialDelta.class, parameters);
	}

	@Override
	public List<com.ubiqube.etsi.mano.service.pkg.bean.VduScalingAspectDeltas> getVduScalingAspectDeltas(final Map<String, String> parameters) {
		return getListOf(VduScalingAspectDeltas.class, com.ubiqube.etsi.mano.service.pkg.bean.VduScalingAspectDeltas.class, parameters);
	}

}
