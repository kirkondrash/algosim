package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.io.Resource;

import javax.validation.Valid;

/**
 * SrcJar
 */

@Getter
@Setter
@ToString
public class SrcJar   {
  @JsonProperty("jar")
  @Valid
  private Resource jar;

}