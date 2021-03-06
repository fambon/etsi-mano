package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * * FORCEFUL: The VNFM will stop the VNF instance immediately after accepting    the request. * GRACEFUL: The VNFM instance will first arrange to take the VNF out of    service after accepting the request. Once that operation is successful    or once the timer value specified in the \"gracefulStopTimeout\" attribute   expires, the VNFM will stop the VNF instance. 
 */
public enum StopType {
  FORCEFUL("FORCEFUL"),
    GRACEFUL("GRACEFUL");

  private String value;

  StopType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StopType fromValue(String text) {
    for (StopType b : StopType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
