package de.htwberlin.mae.configuration;

import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.MappedInterceptor;


import com.fasterxml.jackson.databind.ObjectMapper;


import de.htwberlin.mae.interceptor.RestLimitInterceptor;
import de.htwberlin.mae.model.Article;
import de.htwberlin.mae.model.Customer;
import de.htwberlin.mae.model.Cart;

@Configuration
public class RestConfiguration extends RepositoryRestMvcConfiguration {
	
	
	Logger log = LogManager.getRootLogger();
	
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
		config.exposeIdsFor(Customer.class);
		config.exposeIdsFor(Article.class);
		config.exposeIdsFor(Cart.class);
		return config;
	}

	/**
	 * CUSTOM BEANS
	 */
	
	
	// Customize the default entries in errorAttributes to suit your needs
	@Bean
	public ErrorAttributes errorAttributes() {
	    return new DefaultErrorAttributes() {
	        @Override
	        public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
	            Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
	            DateTime dateTime = new DateTime(errorAttributes.get("timestamp"));
	            errorAttributes.put("timestamp", dateTime.toString());
	            errorAttributes.put("type",  "about:blank");
	            errorAttributes.put("status", HttpStatus.valueOf((Integer) errorAttributes.get("status")));
	            errorAttributes.put("title", errorAttributes.get("error"));
	            errorAttributes.put("detail", errorAttributes.get("message"));
	            errorAttributes.put("instance", errorAttributes.get("path"));
	            errorAttributes.remove("path");
	            errorAttributes.remove("message");
	            errorAttributes.remove("error");	            
	            return errorAttributes;
	        }
	   };
	}
	
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
}
