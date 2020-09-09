package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * SrcStatus
 */

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class SrcStatus   {
  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    @JsonProperty("SOURCE_UPLOADED")
    SOURCE_UPLOADED,
    @JsonProperty("SCHEDULED_FOR_COMPILATION")
    SCHEDULED_FOR_COMPILATION,
    @JsonProperty("COMPILING")
    COMPILING,
    @JsonProperty("SUCCESSFULLY_COMPILED")
    SUCCESSFULLY_COMPILED,
    @JsonProperty("COMPILATION_FAILED")
    COMPILATION_FAILED,
    @JsonProperty("SCHEDULED_FOR_EXECUTION")
    SCHEDULED_FOR_EXECUTION,
    @JsonProperty("EXECUTING")
    EXECUTING,
    @JsonProperty("SUCCESSFULLY_EXECUTED")
    SUCCESSFULLY_EXECUTED,
    @JsonProperty("EXECUTION_FAILED")
    EXECUTION_FAILED;
  }

  @NotNull
  private StatusEnum status;

  @JsonProperty("errorTrace")
  private String errorTrace;

  @JsonProperty("metrics")
  private String metrics;
}