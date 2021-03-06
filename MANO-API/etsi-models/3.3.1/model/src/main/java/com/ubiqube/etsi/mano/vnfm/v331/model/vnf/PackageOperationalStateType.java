package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * - ENABLED: The VNF package is enabled, i.e. it can be used for the creation
 * of new \"Individual VNF instance\" resources. - DISABLED: The VNF package is
 * disabled, i.e. it shall not be used for the creation of further \"Individual
 * VNF instance\" resources (unless and until the VNF package is re-enabled).
 */
public enum PackageOperationalStateType {

	ENABLED("ENABLED"),

	DISABLED("DISABLED");

	private String value;

	PackageOperationalStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PackageOperationalStateType fromValue(final String text) {
		for (final PackageOperationalStateType b : PackageOperationalStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
