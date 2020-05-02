package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * UserCodeInfo
 */
@Validated
public class UserCodeInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("info")
  private String info = null;

  public UserCodeInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "kirkondrash_newalgo", required = true, value = "")
  @NotNull
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserCodeInfo info(String info) {
    this.info = info;
    return this;
  }

  /**
   * Get info
   * @return info
  **/
  @ApiModelProperty(example = "Errors met", value = "")
  
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCodeInfo userCodeInfo = (UserCodeInfo) o;
    return Objects.equals(this.id, userCodeInfo.id) &&
        Objects.equals(this.info, userCodeInfo.info);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, info);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCodeInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
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
