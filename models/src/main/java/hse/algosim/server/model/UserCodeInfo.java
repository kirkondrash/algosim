package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * UserCodeInfo
 */
@Validated
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class UserCodeInfo   {
  @JsonProperty("id")
  @NotNull
  private String id;

  @JsonProperty("info")
  private String info;
}
