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
package com.ubiqube.etsi.mano.dao.mano.v2.nfvo;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;

@Entity
public class NsVnfTask extends NsTask {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private NsdPackageVnfPackage nsPackageVnfPackage;

	private UUID vnfInstance;

	private String vnfdId;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public NsdPackageVnfPackage getNsPackageVnfPackage() {
		return nsPackageVnfPackage;
	}

	public void setNsPackageVnfPackage(final NsdPackageVnfPackage nsPackageVnfPackage) {
		this.nsPackageVnfPackage = nsPackageVnfPackage;
	}

	public UUID getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final UUID vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

}
