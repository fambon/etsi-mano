/*
 * SOL003 - VNF Lifecycle Management interface
 * SOL003 - VNF Lifecycle Management interface definition  IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to. In case of discrepancies the published ETSI Group Specification takes precedence.  In clause 4.3.2 of ETSI GS NFV-SOL 003 v2.4.1, an attribute-based filtering mechanism is defined. This mechanism is currently not included in the corresponding OpenAPI design for this GS version. Changes to the attribute-based filtering mechanism are being considered in v2.5.1 of this GS for inclusion in the corresponding future ETSI NFV OpenAPI design. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis&list_id=61&product=NFV&resolution=
 *
 * OpenAPI spec version: 1.1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.ubiqube.etsi.mano.model.nslcm.sol003;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents network address data for IP over Ethernet.
 */
@ApiModel(description = "This type represents network address data for IP over Ethernet. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
public class IpOverEthernetAddressData {
	@JsonProperty("macAddress")
	private String macAddress = null;

	@JsonProperty("ipAddresses")
	private List<IpOverEthernetAddressDataIpAddresses> ipAddresses = null;

	public IpOverEthernetAddressData macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	/**
	 * MAC address. If this attribute is not present, it shall be chosen by the VIM.
	 * At least one of \&quot;macAddress\&quot; or \&quot;ipAddresses\&quot; shall
	 * be present.
	 * 
	 * @return macAddress
	 **/
	@JsonProperty("macAddress")
	@ApiModelProperty(value = "MAC address. If this attribute is not present, it shall be chosen by the VIM. At least one of \"macAddress\" or \"ipAddresses\" shall be present. ")
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public IpOverEthernetAddressData ipAddresses(List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
		this.ipAddresses = ipAddresses;
		return this;
	}

	public IpOverEthernetAddressData addIpAddressesItem(IpOverEthernetAddressDataIpAddresses ipAddressesItem) {
		if (this.ipAddresses == null) {
			this.ipAddresses = new ArrayList<IpOverEthernetAddressDataIpAddresses>();
		}
		this.ipAddresses.add(ipAddressesItem);
		return this;
	}

	/**
	 * List of IP addresses to assign to the CP instance. Each entry represents IP
	 * address data for fixed or dynamic IP address assignment per subnet. If this
	 * attribute is not present, no IP address shall be assigned.
	 * 
	 * @return ipAddresses
	 **/
	@JsonProperty("ipAddresses")
	@ApiModelProperty(value = "List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. ")
	public List<IpOverEthernetAddressDataIpAddresses> getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class IpOverEthernetAddressData {\n");

		sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
		sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}