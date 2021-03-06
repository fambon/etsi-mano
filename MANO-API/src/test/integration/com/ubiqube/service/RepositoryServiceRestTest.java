package com.ubiqube.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.etsi.mano.service.Configuration;
import com.ubiqube.etsi.mano.service.PropertiesConfiguration;
import com.ubiqube.etsi.mano.service.rest.RepositoryServiceRest;
import com.ubiqube.etsi.mano.service.rest.UbiRest;

public class RepositoryServiceRestTest {
	RepositoryServiceRest repositoryServiceRest;

	public RepositoryServiceRestTest() {
		final Configuration conf = new PropertiesConfiguration();
		repositoryServiceRest = new RepositoryServiceRest(new UbiRest(conf));
	}

	@Test
	public void testDoSearch() throws Exception {
		final List<String> res = repositoryServiceRest.doSearch("Process/ETSI-MANO", null);
		assertNotNull("The list must not be null", res);
	}

	@Test
	public void testExists() throws Exception {
		final boolean res = repositoryServiceRest.exists("Process/ETSI-MANO");
		assertTrue("The element must exist (exists: true)", res);
	}

	@Test
	public void testGetElement() throws Exception {
		final RepositoryElement res = repositoryServiceRest.getElement("Process/ETSI-MANO");
		assertNotNull("The element must not be null", res);
		assertEquals("The name must be equal to ETSI-MANO", res.getName(), "ETSI-MANO");
	}

	@Test
	public void testGetRepositoryElementContent() throws Exception {
		final RepositoryElement element = repositoryServiceRest.getElement(
				"Process/ETSI-MANO/NFV/Common/Tasks/Task_Synchronize_to_VIM_tenant.php");
		final byte res[] = repositoryServiceRest.getRepositoryElementContent(element);
		assertNotNull("The element must not be null", res);
		assertTrue("The element must be PHP code", new String(res).startsWith("<?php"));
	}
}
