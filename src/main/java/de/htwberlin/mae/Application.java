package de.htwberlin.mae;


import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"de.htwberlin.mae.model",
				"de.htwberlin.mae.repository",
				"de.htwberlin.mae"})
public class Application {

    public static void main(String[] args) {
    	if(System.getenv("ENV_SYSTEM") == null) {
    		Properties props = System.getProperties();
    		props.setProperty("ENV_SYSTEM", "local");
    	}
    	System.out.println("ENV_SYSTEM: " + System.getProperty("ENV_SYSTEM"));
        SpringApplication.run(Application.class, args);
    }

}
