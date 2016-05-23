package de.htwberlin.mae;


import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"de.htwberlin.mae.model",
				"de.htwberlin.mae.repository",
				"de.htwberlin.mae"})
public class Application {

    public static void main(String[] args) {
    	System.out.println("ENVIRONMENT: " + System.getenv("ENVIRONMENT"));
        //SpringApplication.run(Application.class, args);
    }

}
