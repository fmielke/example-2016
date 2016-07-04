package de.htwberlin.mae.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import static de.htwberlin.mae.oauth2.OAuth2Configuration.PERMISSION_NUTZER_DELETE;
import static de.htwberlin.mae.oauth2.OAuth2Configuration.PERMISSION_NUTZER_READ;
import static de.htwberlin.mae.oauth2.OAuth2Configuration.PERMISSION_NUTZER_WRITE;

/**
 * Created by fmielke on 29.06.16.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(ResourceServerConfiguration.class);

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/nutzer/**").hasAuthority(PERMISSION_NUTZER_READ)
                .antMatchers(HttpMethod.GET, "/api/**/**/nutzer/**").hasAuthority(PERMISSION_NUTZER_READ)
                .antMatchers(HttpMethod.POST, "/api/nutzer/**").hasAuthority(PERMISSION_NUTZER_WRITE)
                .antMatchers(HttpMethod.POST, "/api/**/**/nutzer/**").hasAuthority(PERMISSION_NUTZER_WRITE)
                .antMatchers(HttpMethod.DELETE, "/api/nutzer/**").hasAuthority(PERMISSION_NUTZER_DELETE)
        		.antMatchers(HttpMethod.DELETE, "/api/**/**/nutzer/**").hasAuthority(PERMISSION_NUTZER_DELETE);
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
