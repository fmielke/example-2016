package de.htwberlin.mae;


import de.htwberlin.mae.configuration.EnvironmentConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableJpaAuditing //enable @Version tag in Entities
@SpringBootApplication //same as @Configuration @EnableAutoConfiguration and @ComponentScan
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
    	
    	//set environment
    	EnvironmentConfiguration.setEnvironmentProperties();
    	
        SpringApplication.run(Application.class, args);
    }   
}