package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotNull;

/**
 * UserCodeInfo
 */
@Builder
public class UserCodeInfo   {
  @JsonProperty("id")
  @NotNull
  private final String id;

  @JsonProperty("info")
  @NotNull
  private final String info;
}
