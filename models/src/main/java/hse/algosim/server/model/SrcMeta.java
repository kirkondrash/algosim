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

/**
 * SrcMeta
 */

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meta")
public class SrcMeta {
  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JsonIgnore
  @Column(name = "algo_id")
  private String algoUserId;

  @JsonProperty("description")
  private String description;

  @JsonProperty("author")
  private String author;

  @JsonProperty("models")
  @OneToMany(cascade = CascadeType.ALL)
  private Set<ModelToAlgo> models;
}