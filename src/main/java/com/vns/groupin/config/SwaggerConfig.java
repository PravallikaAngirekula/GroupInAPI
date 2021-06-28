package com.vns.groupin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("Group In").apiInfo(apiInfo()).select().paths(regex("/api-groupin.app/GroupIn.*")).build();
		return  new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.vns.groupin"))
				.build();
	}

//	private ApiInfo apiInfo() {
//		// TODO Auto-generated method stub
//		return new ApiInfoBuilder().title("Group In Services")
//				.description("Sample Documentation  Generated Using Swagger2 for our REST API")
//				.build() ;
//	}
}
