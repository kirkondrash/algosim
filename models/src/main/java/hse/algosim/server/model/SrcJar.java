package hse.algosim.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.io.Resource;

import javax.validation.Valid;
import java.util.Objects;

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
  public boolean equals(Object o) {
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}