package de.htwberlin.mae.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fmielke on 29.06.16.
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    Logger log = LogManager.getRootLogger();

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
		http		
		.exceptionHandling()
		.authenticationEntryPoint((request, response, authenticationException) -> { 
		log.info("BAAAAM");
	    response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    response.getOutputStream().println("{ \"error\": \"" + authenticationException.getMessage() + "\" }");
	    })
		.and()
        .authorizeRequests()
        .anyRequest();
		
    }
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	log.info("AUTHMANAGER");
    	
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }
}
