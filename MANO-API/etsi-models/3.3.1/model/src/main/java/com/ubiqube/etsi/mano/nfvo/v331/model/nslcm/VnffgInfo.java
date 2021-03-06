package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.NsCpHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information on the VNFFG(s) of the NS instance. 
 */
@Schema(description = "Information on the VNFFG(s) of the NS instance. ")
@Validated


public class VnffgInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnffgdId")
  private String vnffgdId = null;

  @JsonProperty("vnfInstanceId")
  @Valid
  private List<String> vnfInstanceId = new ArrayList<>();

  @JsonProperty("pnfdInfoId")
  @Valid
  private List<String> pnfdInfoId = null;

  @JsonProperty("nsVirtualLinkInfoId")
  @Valid
  private List<String> nsVirtualLinkInfoId = null;

  @JsonProperty("nsCpHandle")
  private NsCpHandle nsCpHandle = null;

  public VnffgInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnffgInfo vnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
    return this;
  }

  /**
   * Get vnffgdId
   * @return vnffgdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnffgdId() {
    return vnffgdId;
  }

  public void setVnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
  }

  public VnffgInfo vnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  public VnffgInfo addVnfInstanceIdItem(String vnfInstanceIdItem) {
    this.vnfInstanceId.add(vnfInstanceIdItem);
    return this;
  }

  /**
   * Identifier(s) of the constituent VNF instance(s) of this VNFFG instance. 
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "Identifier(s) of the constituent VNF instance(s) of this VNFFG instance. ")
      @NotNull

    public List<String> getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public VnffgInfo pnfdInfoId(List<String> pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
    return this;
  }

  public VnffgInfo addPnfdInfoIdItem(String pnfdInfoIdItem) {
    if (this.pnfdInfoId == null) {
      this.pnfdInfoId = new ArrayList<>();
    }
    this.pnfdInfoId.add(pnfdInfoIdItem);
    return this;
  }

  /**
   * Identifier(s) of the constituent PNF instance(s) of this VNFFG instance. 
   * @return pnfdInfoId
   **/
  @Schema(description = "Identifier(s) of the constituent PNF instance(s) of this VNFFG instance. ")
  
    public List<String> getPnfdInfoId() {
    return pnfdInfoId;
  }

  public void setPnfdInfoId(List<String> pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
  }

  public VnffgInfo nsVirtualLinkInfoId(List<String> nsVirtualLinkInfoId) {
    this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
    return this;
  }

  public VnffgInfo addNsVirtualLinkInfoIdItem(String nsVirtualLinkInfoIdItem) {
    if (this.nsVirtualLinkInfoId == null) {
      this.nsVirtualLinkInfoId = new ArrayList<>();
    }
    this.nsVirtualLinkInfoId.add(nsVirtualLinkInfoIdItem);
    return this;
  }

  /**
   * Identifier(s) of the constituent VL instance(s) of this VNFFG instance. 
   * @return nsVirtualLinkInfoId
   **/
  @Schema(description = "Identifier(s) of the constituent VL instance(s) of this VNFFG instance. ")
  
    public List<String> getNsVirtualLinkInfoId() {
    return nsVirtualLinkInfoId;
  }

  public void setNsVirtualLinkInfoId(List<String> nsVirtualLinkInfoId) {
    this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
  }

  public VnffgInfo nsCpHandle(NsCpHandle nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
    return this;
  }

  /**
   * Get nsCpHandle
   * @return nsCpHandle
   **/
  @Schema(description = "")
  
    @Valid
    public NsCpHandle getNsCpHandle() {
    return nsCpHandle;
  }

  public void setNsCpHandle(NsCpHandle nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnffgInfo vnffgInfo = (VnffgInfo) o;
    return Objects.equals(this.id, vnffgInfo.id) &&
        Objects.equals(this.vnffgdId, vnffgInfo.vnffgdId) &&
        Objects.equals(this.vnfInstanceId, vnffgInfo.vnfInstanceId) &&
        Objects.equals(this.pnfdInfoId, vnffgInfo.pnfdInfoId) &&
        Objects.equals(this.nsVirtualLinkInfoId, vnffgInfo.nsVirtualLinkInfoId) &&
        Objects.equals(this.nsCpHandle, vnffgInfo.nsCpHandle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnffgdId, vnfInstanceId, pnfdInfoId, nsVirtualLinkInfoId, nsCpHandle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnffgInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnffgdId: ").append(toIndentedString(vnffgdId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
    sb.append("    nsVirtualLinkInfoId: ").append(toIndentedString(nsVirtualLinkInfoId)).append("\n");
    sb.append("    nsCpHandle: ").append(toIndentedString(nsCpHandle)).append("\n");
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
