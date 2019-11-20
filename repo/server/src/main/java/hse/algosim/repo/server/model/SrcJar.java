package hse.algosim.repo.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.io.Resource;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SrcJar
 */

public class SrcJar   {
  @JsonProperty("jar")
  private Resource jar;

  public SrcJar jar(Resource jar) {
    this.jar = jar;
    return this;
  }

  /**
   * Get jar
   * @return jar
  */
  @ApiModelProperty(value = "")

  @Valid

  public Resource getJar() {
    return jar;
  }

  public void setJar(Resource jar) {
    this.jar = jar;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SrcJar srcJar = (SrcJar) o;
    return Objects.equals(this.jar, srcJar.jar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jar);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SrcJar {\n");
    
    sb.append("    jar: ").append(toIndentedString(jar)).append("\n");
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

