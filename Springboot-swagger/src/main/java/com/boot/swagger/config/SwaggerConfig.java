package com.boot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productInfo() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.boot.swagger.controller"))
				.paths(regex("/book.*"))
				.build().apiInfo(apiInfo())
				
				
				.securitySchemes(apiKey())
		        .securityContexts(securityContext())
				
				/*globalOperationParameters(Collections.singletonList(new ParameterBuilder()
						.name("authorization")
						.description("Bearer token")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build()))*/    ;
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Springboot Swagger document")
				.description("Swagger documentation for Book store")
				.version("1.0.0")
				.license("bookstore license")
				.licenseUrl("http://bookstore.com")
				.contact(new Contact("siva", "http://kloud9.nyc", "siva@gmail.com"))
				.build();
	}
	
	 private List<ApiKey> apiKey() {
		 List<ApiKey> lists = new ArrayList<>();
		 lists.add(new ApiKey("mykey", "api_key", "header"));
		    return lists;
	  }

	 private List<SecurityContext> securityContext() {
		 List<SecurityContext> contexts = new ArrayList<>();
		    contexts.add(SecurityContext.builder()
		        .securityReferences(defaultAuth())
		        .forPaths(PathSelectors.regex("/api.*"))
		        .build());
		    return contexts;
	  }

	 List<SecurityReference> defaultAuth() {
		    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		    authorizationScopes[0] = authorizationScope;
		    List<SecurityReference> references = new ArrayList<>();
		    references.add(new SecurityReference("mykey", authorizationScopes));
		    return references;
	  }
}
