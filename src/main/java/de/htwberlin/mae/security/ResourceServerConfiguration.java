package de.htwberlin.mae.security;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by fmielke on 29.06.16.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    Logger log = LogManager.getRootLogger();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .exceptionHandling()
		.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		.and()
		.csrf()
		.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")) 
		.disable()
        .authorizeRequests()
        .regexMatchers(HttpMethod.GET, ".*customers.*").access("#oauth2.hasAnyScope('nutzer.read, nutzer.all')")
        .regexMatchers(HttpMethod.POST, ".*customers.*").access("#oauth2.hasAnyScope('nutzer.write, nutzer.all')")
        .regexMatchers(HttpMethod.PUT, ".*customers.*").access("#oauth2.hasAnyScope('nutzer.write, nutzer.all')")
        .regexMatchers(HttpMethod.DELETE, ".*customers.*").access("#oauth2.hasAnyScope('nutzer.delete, nutzer.all')")

        .regexMatchers(HttpMethod.GET, ".*article.*").access("#oauth2.hasAnyScope('artikel.read, artikel.all')")
        .regexMatchers(HttpMethod.POST, ".*article.*").access("#oauth2.hasAnyScope('artikel.write, artikel.all')")
        .regexMatchers(HttpMethod.PUT, ".*article.*").access("#oauth2.hasAnyScope('artikel.write, artikel.all')")
        .regexMatchers(HttpMethod.DELETE, ".*article.*").access("#oauth2.hasAnyScope('artikel.delete, artikel.all')")

        .regexMatchers(HttpMethod.GET, ".*carts.*").access("#oauth2.hasAnyScope('warenkorb.read, warenkorb.all')")
        .regexMatchers(HttpMethod.POST, ".*carts.*").access("#oauth2.hasAnyScope('warenkorb.write, warenkorb.all')")
        .regexMatchers(HttpMethod.PUT, ".*carts.*").access("#oauth2.hasAnyScope('warenkorb.write, warenkorb.all')")
        .regexMatchers(HttpMethod.DELETE, ".*carts.*").access("#oauth2.hasAnyScope('warenkorb.delete, warenkorb.all')");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        log.info("Configuring ResourceServerSecurityConfigurer ");
        resources.resourceId("oauth2").tokenStore(tokenStore);
    }

    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter tokenConverter;
}
