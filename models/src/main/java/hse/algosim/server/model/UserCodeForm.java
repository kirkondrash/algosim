package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.core.io.Resource;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * UserCodeForm
 */
@Builder(toBuilder = true)
public class UserCodeForm   {
  @JsonProperty("code")
  @NotNull
  private final Resource code;

  @JsonProperty("userId")
  @NotNull
  private final String userId;

  @JsonProperty("userAlgoName")
  @NotNull
  private final String userAlgoName;

  @JsonProperty("models")
  private final List<String> models;

}
