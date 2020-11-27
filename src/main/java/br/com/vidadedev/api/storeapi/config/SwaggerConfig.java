package br.com.vidadedev.api.storeapi.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("br.com.vidadedev.api.storeapi.controller"))              
          .paths(PathSelectors.any())
          .build()
          .useDefaultResponseMessages(false)
          .directModelSubstitute(ResponseEntity.class, Void.class)
          .produces(Collections.singleton("application/json"))
          .apiInfo(metaData());

	  }

	  private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	        .title("Store API")
	        .description("API responsável por expor as principais operações e informações"
	        		+ " relacionadas aos locais cadastrados no banco de dados")
	        .version("1.0.0")
	        .build();
	  }
	  
	  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	  }
	
}
