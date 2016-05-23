package de.htwberlin.mae;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"de.htwberlin.mae.model",
				"de.htwberlin.mae.repository",
				"de.htwberlin.mae"})
public class Application {

    public static void main(String[] args) {
    	System.out.println("ENV_SYSTEM: " + System.getenv("ENV_SYSTEM"));
        SpringApplication.run(Application.class, args);
    }

}
