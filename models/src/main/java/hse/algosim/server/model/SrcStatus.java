package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
    SCHEDULED_FOR_COMPILATION("SCHEDULED FOR COMPILATION"),
    
    COMPILING("COMPILING"),
    
    SUCCESSFULLY_COMPILED("SUCCESSFULLY COMPILED"),
    
    COMPILATION_FAILED("COMPILATION FAILED"),
    
    SCHEDULED_FOR_EXECUTION("SCHEDULED FOR EXECUTION"),
    
    EXECUTING("EXECUTING"),
    
    SUCCESSFULLY_EXECUTED("SUCCESSFULLY EXECUTED"),
    
    EXECUTION_FAILED("EXECUTION FAILED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("status")
  private StatusEnum status;

  @JsonProperty("errorTrace")
  private String errorTrace;

  @JsonProperty("winloss")
  private String winloss;

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

  public SrcStatus winloss(String winloss) {
    this.winloss = winloss;
    return this;
  }

  /**
   * Get winloss
   * @return winloss
  */
  @ApiModelProperty(example = "+200kk", value = "")


  public String getWinloss() {
    return winloss;
  }

  public void setWinloss(String winloss) {
    this.winloss = winloss;
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
        Objects.equals(this.winloss, srcStatus.winloss);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, errorTrace, winloss);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrcStatus {\n");

    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    errorTrace: ").append(toIndentedString(errorTrace)).append("\n");
    sb.append("    winloss: ").append(toIndentedString(winloss)).append("\n");
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