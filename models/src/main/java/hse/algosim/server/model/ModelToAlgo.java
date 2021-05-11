package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model_to_algo")
public class ModelToAlgo {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "name")
  private RecommendationModel model;

  @ManyToOne
  @JoinColumn(name = "algo_id")
  private SrcMeta algo;

  private Integer hostPort;

  private String containerId;

}