package de.htwberlin.mae.security;

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
                .authorizeRequests()
                .regexMatchers(HttpMethod.GET, ".*nutzer.*").access("#oauth2.hasAnyScope('nutzer.read, nutzer.all')")
                .regexMatchers(HttpMethod.POST, ".*nutzer.*").access("#oauth2.hasAnyScope('nutzer.write, nutzer.all')")
                .regexMatchers(HttpMethod.PUT, ".*nutzer.*").access("#oauth2.hasAnyScope('nutzer.write, nutzer.all')")
                .regexMatchers(HttpMethod.DELETE, ".*nutzer.*").access("#oauth2.hasAnyScope('nutzer.delete, nutzer.all')")

                .regexMatchers(HttpMethod.GET, ".*artikel.*").access("#oauth2.hasAnyScope('artikel.read, artikel.all')")
                .regexMatchers(HttpMethod.POST, ".*artikel.*").access("#oauth2.hasAnyScope('artikel.write, artikel.all')")
                .regexMatchers(HttpMethod.PUT, ".*artikel.*").access("#oauth2.hasAnyScope('artikel.write, artikel.all')")
                .regexMatchers(HttpMethod.DELETE, ".*artikel.*").access("#oauth2.hasAnyScope('artikel.delete, artikel.all')")

                .regexMatchers(HttpMethod.GET, ".*warenkorb.*").access("#oauth2.hasAnyScope('warenkorb.read, warenkorb.all')")
                .regexMatchers(HttpMethod.POST, ".*warenkorb.*").access("#oauth2.hasAnyScope('warenkorb.write, warenkorb.all')")
                .regexMatchers(HttpMethod.PUT, ".*warenkorb.*").access("#oauth2.hasAnyScope('warenkorb.write, warenkorb.all')")
                .regexMatchers(HttpMethod.DELETE, ".*warenkorb.*").access("#oauth2.hasAnyScope('warenkorb.delete, warenkorb.all')");
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
