package de.htwberlin.mae.configuration;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.mae.interceptor.RestLimitInterceptor;
import de.htwberlin.mae.model.Artikel;
import de.htwberlin.mae.model.Nutzer;
import de.htwberlin.mae.model.Warenkorb;

@Configuration
public class RestConfiguration extends RepositoryRestMvcConfiguration {

	/**
	 * setting base parameter for application
	 */
	@Override
	public RepositoryRestConfiguration config() {
		RepositoryRestConfiguration config = super.config();
		config.setBasePath("/api/v1");
		config.setDefaultMediaType(MediaType.parseMediaType("application/hal+json"));
		config.setDefaultPageSize(5);
		config.setMaxPageSize(100);
		config.setPageParamName("offset");
		config.setLimitParamName("limit");
		config.exposeIdsFor(Nutzer.class);
		config.exposeIdsFor(Artikel.class);
		config.exposeIdsFor(Warenkorb.class);
		return config;
	}

	// CUSTOM BEANS
	/**
	 * add interceptor for http requests on base Path
	 * 
	 * @return
	 */
	@Bean
	public MappedInterceptor myMappedInterceptor() {
		return new MappedInterceptor(new String[] { "/api/v1/**" }, new RestLimitInterceptor());
	}

	/**
	 * add CORS support
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PATCH");
		config.addAllowedMethod("TRACE");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		// return new CorsFilter(source);
		final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	/**
	 * enable custom vendor specific hal+json support
	 */
	private static final String HAL_OBJECT_MAPPER_BEAN_NAME = "_halObjectMapper";

	@Autowired
	private BeanFactory beanFactory;

	@Bean
	public HttpMessageConverters customConverters() {
		return new HttpMessageConverters(new HalMappingJackson2HttpMessageConverter());
	}

	private class HalMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
		public HalMappingJackson2HttpMessageConverter() {
			setSupportedMediaTypes(Arrays.asList(
					new MediaType("application", "vnd.de.htwberlin.mae.v1+hal+json", Charset.defaultCharset()),
					new MediaType("application", "vnd.de.htwberlin.mae.v1+json", Charset.defaultCharset())));

			ObjectMapper halObjectMapper = beanFactory.getBean(HAL_OBJECT_MAPPER_BEAN_NAME, ObjectMapper.class);
			setObjectMapper(halObjectMapper);
		}
	}
}
