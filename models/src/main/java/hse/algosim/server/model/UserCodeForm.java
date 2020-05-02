package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * UserCodeForm
 */
@Validated
public class UserCodeForm   {
  @JsonProperty("code")
  private Resource code = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userAlgoName")
  private String userAlgoName = null;

  public UserCodeForm code(Resource code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(value = "")
  @NotNull
  @Valid
  public Resource getCode() {
    return code;
  }

  public void setCode(Resource code) {
    this.code = code;
  }

  public UserCodeForm userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UserCodeForm userAlgoName(String userAlgoName) {
    this.userAlgoName = userAlgoName;
    return this;
  }

  /**
   * Get userAlgoName
   * @return userAlgoName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getUserAlgoName() {
    return userAlgoName;
  }

  public void setUserAlgoName(String userAlgoName) {
    this.userAlgoName = userAlgoName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCodeForm userCodeForm = (UserCodeForm) o;
    return Objects.equals(this.code, userCodeForm.code) &&
        Objects.equals(this.userId, userCodeForm.userId) &&
        Objects.equals(this.userAlgoName, userCodeForm.userAlgoName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, userId, userAlgoName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCodeForm {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userAlgoName: ").append(toIndentedString(userAlgoName)).append("\n");
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
