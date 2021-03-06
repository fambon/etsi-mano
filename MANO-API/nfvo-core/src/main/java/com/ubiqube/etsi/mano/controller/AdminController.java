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
package com.ubiqube.etsi.mano.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

import ma.glasnost.orika.MapperFacade;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/admin")
public class AdminController {
	private final VimConnectionInformationJpa vciJpa;
	private final MapperFacade mapper;
	private final VimManager vimManager;

	public AdminController(final VimConnectionInformationJpa vciJpa, final MapperFacade mapper, final VimManager vimManager) {
		super();
		this.vciJpa = vciJpa;
		this.mapper = mapper;
		this.vimManager = vimManager;
	}

	@PostMapping(value = "/vim/register")
	public ResponseEntity<VimConnectionInformation> registerVim(@RequestBody final VimConnectionInformation body) {
		VimConnectionInformation vci = mapper.map(body, VimConnectionInformation.class);
		vci = vciJpa.save(vci);
		vimManager.rebuildCache();
		return ResponseEntity.ok(mapper.map(vci, VimConnectionInformation.class));
	}

	@DeleteMapping(value = "/vim/{id}")
	public ResponseEntity<Void> deleteVim(@PathVariable("id") final String id) {
		vciJpa.deleteById(UUID.fromString(id));
		vimManager.rebuildCache();
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/vim/{id}/connect")
	public ResponseEntity<Map<String, String>> connectVim(@PathVariable("id") final UUID id) {
		final Vim vim = vimManager.getVimById(id);
		final VimConnectionInformation vimconn = vciJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find vim id " + id));
		final Map<String, String> networks = vim.getPublicNetworks(vimconn);
		return ResponseEntity.ok(networks);
	}

	@GetMapping(value = "/vim")
	public ResponseEntity<Iterable<VimConnectionInformation>> listVim() {
		final Iterable<VimConnectionInformation> vci = vciJpa.findAll();
		return ResponseEntity.ok(vci);
	}

}
