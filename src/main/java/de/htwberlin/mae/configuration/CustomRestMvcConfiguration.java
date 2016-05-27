package de.htwberlin.mae.configuration;

import java.nio.charset.Charset;
import java.util.Arrays;



import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class CustomRestMvcConfiguration extends RepositoryRestMvcConfiguration {

	/**
	 * setting base path for the api
	 */
	@Override
	public RepositoryRestConfiguration config() {
		RepositoryRestConfiguration config = super.config();
		config.setBasePath("/api");
		return config;
	}
	
	
	/**
	 * enable custom vendor specific hal+json support
	 */
	@Autowired
    private BeanFactory beanFactory;
	private static final String HAL_OBJECT_MAPPER_BEAN_NAME = "_halObjectMapper";

    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(new HalMappingJackson2HttpMessageConverter());
    }

    private class HalMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public HalMappingJackson2HttpMessageConverter() {
            setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "hal+json"),
                new MediaType("application", "*hal+json")
            ));

            ObjectMapper halObjectMapper = beanFactory.getBean(HAL_OBJECT_MAPPER_BEAN_NAME, ObjectMapper.class);
            setObjectMapper(halObjectMapper);
        }
    }
}
