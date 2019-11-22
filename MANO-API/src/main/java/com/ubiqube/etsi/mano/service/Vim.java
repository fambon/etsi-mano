package com.ubiqube.etsi.mano.service;

import java.util.Map;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

public interface Vim {

	String onVnfInstanceTerminate(Map<String, Object> userData);

	String onVnfInstantiate(String vnfPkgId, Map<String, Object> userData);

	String onNsInstantiate(String nsdId, Map<String, Object> userData);

	String onNsInstanceTerminate(Map<String, Object> userData);

	LcmOperationStateType waitForCompletion(String processId, int seconds);

}