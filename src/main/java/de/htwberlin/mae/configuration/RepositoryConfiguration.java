package de.htwberlin.mae.configuration;

import de.htwberlin.mae.eventhandler.NutzerEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fmielke on 17.07.16.
 */
@Configuration
public class RepositoryConfiguration {

        @Bean
        NutzerEventHandler nutzerEventHandler() {
            return new NutzerEventHandler();
        }
}
