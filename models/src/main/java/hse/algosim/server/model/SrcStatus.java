package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statuses")
public class SrcStatus   {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JsonIgnore
  @Column(name = "algo_id")
  private String algoUserId;

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
    EXECUTION_FAILED,
    @JsonProperty("EXECUTION_TIMED_OUT")
    EXECUTION_TIMED_OUT;
  }

  @NotNull
  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @JsonProperty("errorTrace")
  @Column(name = "error_trace",length = 10000)
  private String errorTrace;

  @JsonProperty("metrics")
  @Column(name = "winloss")
  private String metrics;

  @Override
  public String toString() {
    return "SrcStatus(status=" + this.getStatus() + ", errorTrace=" + this.getErrorTrace() + ", metrics=" + this.getMetrics() + ")";
  }
}