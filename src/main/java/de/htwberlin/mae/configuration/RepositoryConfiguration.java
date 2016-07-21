package de.htwberlin.mae.configuration;

import de.htwberlin.mae.eventhandler.CustomerEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fmielke on 17.07.16.
 */
@Configuration
public class RepositoryConfiguration {

        @Bean
        CustomerEventHandler nutzerEventHandler() {
            return new CustomerEventHandler();
        }
}
