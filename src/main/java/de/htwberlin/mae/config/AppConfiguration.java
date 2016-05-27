package de.htwberlin.mae.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class AppConfiguration {

	public AppConfiguration() {}
	
	
	/**
	 * this method sets ENV_SYSTEM variable. This is required to load the proper application-{ENV_SYSTEM}.properties file
	 * SYSTEM_ENV is "heroku" if application runs in heroku or "local" if application runs local
	 */
	public static void setEnvironmentProperties(){
		Logger logger = LoggerFactory.getLogger(AppConfiguration.class);
		if(System.getenv("ENV_SYSTEM") == null) {
    		Properties props = System.getProperties();
    		props.setProperty("ENV_SYSTEM", "local");
    		System.out.println("ENV_SYSTEM: " + System.getProperty("ENV_SYSTEM"));
			logger.info("ENV_SYSTEM: {}", System.getProperty("ENV_SYSTEM"));
    	}
    	else {
    		System.out.println("ENV_SYSTEM: " + System.getenv("ENV_SYSTEM"));
    	}
	}
}
