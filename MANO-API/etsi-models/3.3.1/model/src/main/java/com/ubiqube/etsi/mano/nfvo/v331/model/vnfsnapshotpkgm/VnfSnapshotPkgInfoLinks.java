package com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class VnfSnapshotPkgInfoLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("packageContent")
  private Link packageContent = null;

  public VnfSnapshotPkgInfoLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public VnfSnapshotPkgInfoLinks packageContent(Link packageContent) {
    this.packageContent = packageContent;
    return this;
  }

  /**
   * Get packageContent
   * @return packageContent
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getPackageContent() {
    return packageContent;
  }

  public void setPackageContent(Link packageContent) {
    this.packageContent = packageContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfSnapshotPkgInfoLinks vnfSnapshotPkgInfoLinks = (VnfSnapshotPkgInfoLinks) o;
    return Objects.equals(this.self, vnfSnapshotPkgInfoLinks.self) &&
        Objects.equals(this.packageContent, vnfSnapshotPkgInfoLinks.packageContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, packageContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotPkgInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    packageContent: ").append(toIndentedString(packageContent)).append("\n");
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
