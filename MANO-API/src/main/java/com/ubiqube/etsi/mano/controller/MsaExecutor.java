package com.ubiqube.etsi.mano.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.GenericException;

@Service
public class MsaExecutor {
	private static final String CUSTOMER_ID = "customerId";
	private final OrchestrationService orchestrationService;

	public MsaExecutor(OrchestrationService orchestrationService) {
		super();
		this.orchestrationService = orchestrationService;
	}

	public String onInstanceTerminate(Map<String, String> userData) {
		final String msaServiceId = userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	public String onInstantiate(String vnfPkgId, Map<String, String> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = userData.get(CUSTOMER_ID);
		varsMap.put("vnfPkgId", vnfPkgId);
		varsMap.put(CUSTOMER_ID, customerId);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	public String onNsInstantiate(String nsdId, Map<String, String> userData) {
		final Map<String, String> varsMap = new HashMap<>();
		final String customerId = userData.get(CUSTOMER_ID);
		varsMap.put("deviceid", userData.get("vimId"));
		varsMap.put("nsPkgId", nsdId);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, 0, SERVICE_NAME, PROCESS_NAME, varsMap);
	}

	public String onNsInstanceTerminate(Map<String, String> userData) {
		final String msaServiceId = userData.get("msaServiceId");
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = userData.get(CUSTOMER_ID);

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";

		return executeProcess(customerId, serviceId, SERVICE_NAME, PROCESS_NAME);
	}

	private String executeProcess(String customerId, long serviceId, String serviceName, String processName) {
		final Map<String, String> varsMap = new HashMap<>();
		return executeProcess(customerId, serviceId, serviceName, processName, varsMap);
	}

	private String executeProcess(String customerId, long serviceId, String serviceName, String processName, Map<String, String> varsMap) {
		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, serviceName, processName, varsMap);
			return String.valueOf(resp.serviceId.id);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

}