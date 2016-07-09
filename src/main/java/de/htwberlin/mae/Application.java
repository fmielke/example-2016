package de.htwberlin.mae;


import de.htwberlin.mae.configuration.AppConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //enable @Version tag in Entities
@SpringBootApplication //same as @Configuration @EnableAutoConfiguration and @ComponentScan
public class Application {

    public static void main(String[] args) {
    	
    	//set environment
    	AppConfiguration.setEnvironmentProperties();
    	
        SpringApplication.run(Application.class, args);
        
        
    }

}