package de.htwberlin.mae;


import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import de.htwberlin.mae.config.AppConfiguration;

@SpringBootApplication
@ComponentScan({"de.htwberlin.mae.model",
				"de.htwberlin.mae.repository",
				"de.htwberlin.mae"})
public class Application {

    public static void main(String[] args) {
    	
    	//set environment
    	AppConfiguration.setEnvironmentProperties();
    	
        SpringApplication.run(Application.class, args);
    }

}
