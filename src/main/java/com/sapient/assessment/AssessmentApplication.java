package com.sapient.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.sapient.assessment.converters.JavaScriptMessageConverter;

@SpringBootApplication
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	
	@Bean
	public RestTemplate restTemplate(JavaScriptMessageConverter jsConverter) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(jsConverter);
		return restTemplate;
	}
}

