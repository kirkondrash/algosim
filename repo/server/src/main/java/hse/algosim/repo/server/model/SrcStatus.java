package hse.algosim.repo.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SrcStatus
 */

public class SrcStatus   {
  @JsonProperty("status")
  private String status;

  @JsonProperty("rank")
  private Integer rank;

  @JsonProperty("winloss")
  private String winloss;

  public SrcStatus status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(example = "SCHEDULED", required = true, value = "")
  @NotNull


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public SrcStatus rank(Integer rank) {
    this.rank = rank;
    return this;
  }

  /**
   * Get rank
   * @return rank
  */
  @ApiModelProperty(value = "")


  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SrcStatus srcStatus = (SrcStatus) o;
    return Objects.equals(this.status, srcStatus.status) &&
        Objects.equals(this.rank, srcStatus.rank) &&
        Objects.equals(this.winloss, srcStatus.winloss);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, rank, winloss);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrcStatus {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    winloss: ").append(toIndentedString(winloss)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

