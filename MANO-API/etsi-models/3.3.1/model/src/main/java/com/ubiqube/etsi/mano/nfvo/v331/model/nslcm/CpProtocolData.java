package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.IpOverEthernetAddressData;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents network protocol data. 
 */
@Schema(description = "This type represents network protocol data. ")
@Validated


public class CpProtocolData   {
  /**
   * Identifier of layer(s) and protocol(s). Permitted values: IP_OVER_ETHERNET. 
   */
  public enum LayerProtocolEnum {
    ETHERNET("IP_OVER_ETHERNET");

    private String value;

    LayerProtocolEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LayerProtocolEnum fromValue(String text) {
      for (LayerProtocolEnum b : LayerProtocolEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("layerProtocol")
  private LayerProtocolEnum layerProtocol = null;

  @JsonProperty("ipOverEthernet")
  private IpOverEthernetAddressData ipOverEthernet = null;

  public CpProtocolData layerProtocol(LayerProtocolEnum layerProtocol) {
    this.layerProtocol = layerProtocol;
    return this;
  }

  /**
   * Identifier of layer(s) and protocol(s). Permitted values: IP_OVER_ETHERNET. 
   * @return layerProtocol
   **/
  @Schema(required = true, description = "Identifier of layer(s) and protocol(s). Permitted values: IP_OVER_ETHERNET. ")
      @NotNull

    public LayerProtocolEnum getLayerProtocol() {
    return layerProtocol;
  }

  public void setLayerProtocol(LayerProtocolEnum layerProtocol) {
    this.layerProtocol = layerProtocol;
  }

  public CpProtocolData ipOverEthernet(IpOverEthernetAddressData ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
    return this;
  }

  /**
   * Get ipOverEthernet
   * @return ipOverEthernet
   **/
  @Schema(description = "")
  
    @Valid
    public IpOverEthernetAddressData getIpOverEthernet() {
    return ipOverEthernet;
  }

  public void setIpOverEthernet(IpOverEthernetAddressData ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CpProtocolData cpProtocolData = (CpProtocolData) o;
    return Objects.equals(this.layerProtocol, cpProtocolData.layerProtocol) &&
        Objects.equals(this.ipOverEthernet, cpProtocolData.ipOverEthernet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layerProtocol, ipOverEthernet);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CpProtocolData {\n");
    
    sb.append("    layerProtocol: ").append(toIndentedString(layerProtocol)).append("\n");
    sb.append("    ipOverEthernet: ").append(toIndentedString(ipOverEthernet)).append("\n");
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
