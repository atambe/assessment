package com.sapient.assessment.converters;


import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
public class JavaScriptMessageConverter extends AbstractJackson2HttpMessageConverter {
	// Add a new mediatype converter
	private JavaScriptMessageConverter() {
		super(Jackson2ObjectMapperBuilder.json().build(), new MediaType("application", "javascript"));
	}

}
