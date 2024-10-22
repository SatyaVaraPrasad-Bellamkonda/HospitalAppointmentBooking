package com.uservalidation.uservalidation_hospital.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserValidationConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
