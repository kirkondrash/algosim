package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model")
public class RecommendationModel {
  @Id
//  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("name")
  @NotNull
  private String name;

  @JsonProperty("container_port")
  @NotNull
  private String containerPort;

  @JsonProperty("additional_args")
  private String additionalArgs;

  @JsonProperty("description")
  private String description;

}