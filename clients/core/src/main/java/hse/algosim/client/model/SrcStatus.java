package hse.algosim.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.util.Objects;

/**
 * SrcStatus
 */

public class SrcStatus {
  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
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

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private StatusEnum status;

  public static final String SERIALIZED_NAME_ERROR_TRACE = "errorTrace";
  @SerializedName(SERIALIZED_NAME_ERROR_TRACE)
  private String errorTrace;

  public static final String SERIALIZED_NAME_METRICS = "metrics";
  @SerializedName(SERIALIZED_NAME_METRICS)
  private String metrics;


  public SrcStatus status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")

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
  **/
  @javax.annotation.Nullable
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
  **/
  @javax.annotation.Nullable
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

