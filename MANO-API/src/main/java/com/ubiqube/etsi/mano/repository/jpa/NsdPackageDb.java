package com.ubiqube.etsi.mano.repository.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NsdRepository;

import ma.glasnost.orika.MapperFacade;

public class NsdPackageDb extends AbstractJpa<NsDescriptorsNsdInfo, NsdPackage> implements NsdRepository {

	public NsdPackageDb(final NsdPackageJpa repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final EntityManager _em) {
		super(_em, repository, mapper, contentManager, jsonMapper);
	}

	@Override
	protected Class getFrontClass() {
		return NsDescriptorsNsdInfo.class;
	}

	@Override
	protected Class getDbClass() {
		return NsdPackage.class;
	}

	@Override
	protected Map<String, From<?, ?>> getJoin(final Root root) {
		final Map<String, From<?, ?>> joins = new HashMap<>();
		joins.put("ROOT", root);
		return joins;
	}

	@Override
	public void changeNsdUpdateState(final NsDescriptorsNsdInfo nsdInfo, final NsdUsageStateEnum state) {
		nsdInfo.setNsdUsageState(state);
		save(nsdInfo);
	}

}
