package de.htwberlin.mae.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class EnvironmentConfiguration {

	public EnvironmentConfiguration() {}
	
	
	/**
	 * this method sets ENV_SYSTEM variable. This is required to load the proper application-{ENV_SYSTEM}.properties file
	 * SYSTEM_ENV is "heroku" if application runs in heroku or "local" if application runs local
	 */
	public static void setEnvironmentProperties(){

        Logger log = LogManager.getRootLogger();

		if(System.getenv("ENV_SYSTEM") == null) {
    		Properties props = System.getProperties();
    		props.setProperty("ENV_SYSTEM", "local");
    		System.out.println("ENV_SYSTEM: " + System.getProperty("ENV_SYSTEM"));
            log.info("ENV_SYSTEM: {}", System.getProperty("ENV_SYSTEM"));
			log.info("LOGENTRIES_TOKEN: {}",System.getenv().get("LOGENTRIES_TOKEN"));
			log.info("JWT_SECRET: {}",System.getenv().get("JWT_SECRET"));
    	}
    	else {
    		System.out.println("ENV_SYSTEM: " + System.getenv("ENV_SYSTEM"));
    	}
	}
}
