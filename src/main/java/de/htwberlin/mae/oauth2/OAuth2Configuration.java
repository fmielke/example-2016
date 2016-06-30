package de.htwberlin.mae.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * Created by fmielke on 29.06.16.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter{

    public static String PERMISSION_NUTZER_READ = "NUTZER_READ";
    public static String PERMISSION_NUTZER_WRITE = "NUTZER_WRITE";
    public static String PERMISSION_NUTZER_DELETE = "NUTZER_DELETE";

    Logger log = LoggerFactory.getLogger(OAuth2Configuration.class);

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("api_client")
                .scopes("CRM_SYSTEM")
                .autoApprove(true)
                .authorities(PERMISSION_NUTZER_READ, PERMISSION_NUTZER_WRITE, PERMISSION_NUTZER_DELETE)
                .authorizedGrantTypes("implicit","refresh_token", "password", "authorization_code", "client_credentials");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        log.info("Created endpoints");
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager);
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Bean
    public TokenStore tokenStore() {
        log.info("Created tokenStore");
        return new JwtTokenStore(jwtTokenEnhancer());
    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        log.info("Start convert in OAuth2Configuration.java");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }
}
