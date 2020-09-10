package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "statuses")
public class SrcStatus   {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    EXECUTION_FAILED;
  }

  @NotNull
  private StatusEnum status;

  @JsonProperty("errorTrace")
  @Column(name = "error_trace")
  private String errorTrace;

  @JsonProperty("metrics")
  @Column(name = "winloss")
  private String metrics;
}