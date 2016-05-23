package de.htwberlin.mae;


import de.htwberlin.mae.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	
    	//set environment
    	AppConfiguration.setEnvironmentProperties();
    	
        SpringApplication.run(Application.class, args);
    }

}
