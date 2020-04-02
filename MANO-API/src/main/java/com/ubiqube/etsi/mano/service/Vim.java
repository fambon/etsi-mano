package com.ubiqube.etsi.mano.service;

import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.service.vim.VimStatus;

public interface Vim {

	String onVnfInstanceTerminate(Map<String, Object> userData);

	String onVnfInstantiate(GrantInformation grantInformation, VnfPackage vnfPackage);

	String onNsInstantiate(String nsdId, Map<String, Object> userData);

	String onNsInstanceTerminate(String processId, Map<String, Object> userData);

	@Nonnull
	VimStatus waitForCompletion(String processId, int seconds);

	void allocateResources(VimConnectionInformation vimConnectionInformation, GrantInformation grantInformation);

	void freeResources(GrantInformation grantInformation);

	String getType();

}