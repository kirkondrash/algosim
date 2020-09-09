package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SrcMeta
 */

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class SrcMeta   {
  @JsonProperty("description")
  private String description;

  @JsonProperty("author")
  private String author;

}