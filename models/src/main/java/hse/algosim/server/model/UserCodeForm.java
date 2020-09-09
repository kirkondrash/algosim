package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * UserCodeForm
 */
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Validated
public class UserCodeForm   {
  @JsonProperty("code")
  @NotNull
  private Resource code;

  @JsonProperty("userId")
  @NotNull
  private String userId;

  @JsonProperty("userAlgoName")
  @NotNull
  private String userAlgoName;

}
