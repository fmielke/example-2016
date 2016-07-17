package de.htwberlin.mae.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    public static final String PERMISSION_NUTZER_READ = "nutzer.read";
    public static final String PERMISSION_NUTZER_WRITE = "nutzer.write";
    public static final String PERMISSION_NUTZER_DELETE = "nutzer.delete";
    public static final String PERMISSION_NUTZER_ALL = "nutzer.all";

    public static final String PERMISSION_ARTIKEL_READ = "artikel.read";
    public static final String PERMISSION_ARTIKEL_WRITE = "artikel.write";
    public static final String PERMISSION_ARTIKEL_DELETE = "artikel.delete";
    public static final String PERMISSION_ARTIKEL_ALL = "artikel.all";

    public static final String PERMISSION_WARENKORB_READ = "warenkorb.read";
    public static final String PERMISSION_WARENKORB_WRITE = "warenkorb.write";
    public static final String PERMISSION_WARENKORB_DELETE = "warenkorb.delete";
    public static final String PERMISSION_WARENKORB_ALL = "warenkorb.all";

    Logger log = LogManager.getRootLogger();

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("api_client")
                .scopes(PERMISSION_ARTIKEL_READ, PERMISSION_WARENKORB_ALL)
                .autoApprove(true)
                .authorizedGrantTypes("implicit","refresh_token", "password", "authorization_code", "client_credentials")
                .and()
                .withClient("api_client_seller")
                .scopes(PERMISSION_WARENKORB_READ, PERMISSION_ARTIKEL_ALL, PERMISSION_NUTZER_READ)
                .autoApprove(true)
                .authorities("ROLE_SELLER_CLIENT")
                .authorizedGrantTypes("implicit","refresh_token", "password", "authorization_code", "client_credentials")
                .and()
                .withClient("api_client_admin")
                .scopes(PERMISSION_NUTZER_ALL, PERMISSION_ARTIKEL_ALL, PERMISSION_WARENKORB_ALL)
                .autoApprove(true)
                .authorities("ROLE_ADMIN_CLIENT")
                .authorizedGrantTypes("implicit","refresh_token", "password", "authorization_code", "client_credentials");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //TODO endpoints.pathMapping("/oauth/token", "/api/v1/oauth/token");
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager);
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        log.info("jwt secret is: " +System.getenv().get("JWT_SECRET") +" and logentries token is " +System.getenv().get("LOGENTRIES_TOKEN"));
        log.info("jwt secret is: " +jwtSecret);
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), jwtSecret.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }
}
