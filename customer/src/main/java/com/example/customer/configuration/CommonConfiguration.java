package com.example.customer.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
