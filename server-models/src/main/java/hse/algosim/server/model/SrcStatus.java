package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * SrcStatus
 */

public class SrcStatus   {
  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
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

  private StatusEnum status;

  @JsonProperty("errorTrace")
  private String errorTrace;

  @JsonProperty("metrics")
  private String metrics;

  public SrcStatus status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public SrcStatus errorTrace(String errorTrace) {
    this.errorTrace = errorTrace;
    return this;
  }

  /**
   * Get errorTrace
   * @return errorTrace
  */
  @ApiModelProperty(value = "")
  public String getErrorTrace() {
    return errorTrace;
  }

  public void setErrorTrace(String errorTrace) {
    this.errorTrace = errorTrace;
  }

  public SrcStatus metrics(String metrics) {
    this.metrics = metrics;
    return this;
  }

  /**
   * Get metrics
   * @return metrics
  */
  @ApiModelProperty(example = "+200kk", value = "")
  public String getMetrics() {
    return metrics;
  }

  public void setMetrics(String metrics) {
    this.metrics = metrics;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SrcStatus srcStatus = (SrcStatus) o;
    return Objects.equals(this.status, srcStatus.status) &&
        Objects.equals(this.errorTrace, srcStatus.errorTrace) &&
        Objects.equals(this.metrics, srcStatus.metrics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, errorTrace, metrics);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrcStatus {\n");

    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    errorTrace: ").append(toIndentedString(errorTrace)).append("\n");
    sb.append("    metrics: ").append(toIndentedString(metrics)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}