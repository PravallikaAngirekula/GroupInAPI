package com.vns.groupin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GroupInApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(GroupInApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(GroupInApplication.class, args);
	}
	
	
	  @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}
