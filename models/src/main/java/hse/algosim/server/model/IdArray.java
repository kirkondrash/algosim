package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

/**
 * IdArray
 */

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class IdArray   {
  @JsonProperty("id")
  @Valid
  @Singular("id")
  private List<String> id;
}