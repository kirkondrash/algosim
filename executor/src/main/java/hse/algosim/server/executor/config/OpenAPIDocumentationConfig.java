package hse.algosim.server.executor.config;

import hse.algosim.server.config.OpenAPIDocumentationBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;


@Configuration
@EnableSwagger2
public class OpenAPIDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Algosim-executor")
            .description("A collab project of HSE and Deutsche Bank aiming to improve trading algorithms testing. Gets the artifact from nexus and executes it.")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("0.0.1")
            .contact(new Contact("","", "kirkondrash@yandex.ru"))
            .build();
    }

    @Bean
    public Docket customImplementation(ServletContext servletContext, @Value("${openapi.algosimExecutor.base-path:/api}") String basePath) {
        return OpenAPIDocumentationBase.customImplementation(servletContext, basePath, "hse.algosim.server.executor.api", apiInfo());
    }

}
