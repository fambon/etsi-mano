package com.ubiqube.etsi.mano.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * NFVO+VNFM & NVFO MSA implementation.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MsaExecutor {
	private static final String CUSTOMER_ID = "customerId";

	private static final Logger LOG = LoggerFactory.getLogger(MsaExecutor.class);

	private final OrchestrationService orchestrationService;

	public MsaExecutor(final OrchestrationService orchestrationService) {
		super();
		this.orchestrationService = orchestrationService;
	}

	public String onVnfInstanceTerminate(final Map<String, Object> userData) {
		final String msaServiceId = (String) userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = (String) userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	public String onVnfInstantiate(final String vnfPkgId, final Map<String, Object> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = (String) userData.get(CUSTOMER_ID);
		varsMap.put("vnfPkgId", vnfPkgId);
		varsMap.put(CUSTOMER_ID, customerId);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	public String onNsInstantiate(final String nsdId, final Map<String, Object> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = (String) userData.get(CUSTOMER_ID);
		varsMap.put("deviceid", (String) userData.get("vimId"));
		varsMap.put("nsPkgId", nsdId);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	public String onNsInstanceTerminate(final Map<String, Object> userData) {
		final String msaServiceId = (String) userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = (String) userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	private String executeProcess(final String customerId, final long serviceId, final String serviceName, final String processName) {
		final Map<String, String> varsMap = new HashMap<>();
		return executeProcess(customerId, serviceId, serviceName, processName, varsMap);
	}

	private String executeProcess(final String customerId, final long serviceId, final String serviceName, final String processName, final Map<String, String> varsMap) {
		try {
			LOG.info("Calling MSA remote FW: custormerId={}, serviceId={}, serviceName={}, processName={}, params={}", customerId, serviceId, serviceName, processName, varsMap);
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, serviceName, processName, varsMap);
			return String.valueOf(resp.getServiceId().getId());
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	public void waitForProcess(final String processId) {
		// TODO This one is probably the same as Polling job.

	}

}
