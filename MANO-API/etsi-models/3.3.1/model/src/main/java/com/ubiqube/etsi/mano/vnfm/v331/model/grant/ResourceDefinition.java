package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.ResourceHandle;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.SnapshotResourceDefinition;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information of an existing or proposed resource used by the VNF. 
 */
@Schema(description = "This type provides information of an existing or proposed resource used by the VNF. ")
@Validated


public class ResourceDefinition   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT 
   */
  public enum TypeEnum {
    COMPUTE("COMPUTE"),
    
    VL("VL"),
    
    STORAGE("STORAGE"),
    
    LINKPORT("LINKPORT");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("resourceTemplateId")
  private String resourceTemplateId = null;

  @JsonProperty("resource")
  private ResourceHandle resource = null;

  @JsonProperty("snapshotResDef")
  private SnapshotResourceDefinition snapshotResDef = null;

  public ResourceDefinition id(String id) {
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

  public ResourceDefinition type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT 
   * @return type
   **/
  @Schema(required = true, description = "Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT ")
      @NotNull

    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ResourceDefinition vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Get vduId
   * @return vduId
   **/
  @Schema(description = "")
  
    public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public ResourceDefinition vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public ResourceDefinition resourceTemplateId(String resourceTemplateId) {
    this.resourceTemplateId = resourceTemplateId;
    return this;
  }

  /**
   * Get resourceTemplateId
   * @return resourceTemplateId
   **/
  @Schema(description = "")
  
    public String getResourceTemplateId() {
    return resourceTemplateId;
  }

  public void setResourceTemplateId(String resourceTemplateId) {
    this.resourceTemplateId = resourceTemplateId;
  }

  public ResourceDefinition resource(ResourceHandle resource) {
    this.resource = resource;
    return this;
  }

  /**
   * Get resource
   * @return resource
   **/
  @Schema(description = "")
  
    @Valid
    public ResourceHandle getResource() {
    return resource;
  }

  public void setResource(ResourceHandle resource) {
    this.resource = resource;
  }

  public ResourceDefinition snapshotResDef(SnapshotResourceDefinition snapshotResDef) {
    this.snapshotResDef = snapshotResDef;
    return this;
  }

  /**
   * Get snapshotResDef
   * @return snapshotResDef
   **/
  @Schema(description = "")
  
    @Valid
    public SnapshotResourceDefinition getSnapshotResDef() {
    return snapshotResDef;
  }

  public void setSnapshotResDef(SnapshotResourceDefinition snapshotResDef) {
    this.snapshotResDef = snapshotResDef;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceDefinition resourceDefinition = (ResourceDefinition) o;
    return Objects.equals(this.id, resourceDefinition.id) &&
        Objects.equals(this.type, resourceDefinition.type) &&
        Objects.equals(this.vduId, resourceDefinition.vduId) &&
        Objects.equals(this.vnfdId, resourceDefinition.vnfdId) &&
        Objects.equals(this.resourceTemplateId, resourceDefinition.resourceTemplateId) &&
        Objects.equals(this.resource, resourceDefinition.resource) &&
        Objects.equals(this.snapshotResDef, resourceDefinition.snapshotResDef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, vduId, vnfdId, resourceTemplateId, resource, snapshotResDef);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceDefinition {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    resourceTemplateId: ").append(toIndentedString(resourceTemplateId)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
    sb.append("    snapshotResDef: ").append(toIndentedString(snapshotResDef)).append("\n");
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
