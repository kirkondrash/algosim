package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.io.Resource;

import javax.validation.Valid;

/**
 * SrcCode
 */

@Getter
@Setter
@ToString
public class SrcCode   {
  @JsonProperty("code")
  @Valid
  private Resource code;

}