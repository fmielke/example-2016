package de.htwberlin.mae.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class RestValidationConfiguration extends RepositoryRestConfigurerAdapter {

	@Bean
	@Primary
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	// validating repositories
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {

		// bean validation always before save and create
		validatingListener.addValidator("afterCreate", validator());
		validatingListener.addValidator("beforeCreate", validator());
		validatingListener.addValidator("afterSave", validator());
		validatingListener.addValidator("beforeSave", validator());
	}
}