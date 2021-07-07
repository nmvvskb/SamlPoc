package com.taxilla.SamlPoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.*;
import org.springframework.security.saml.key.KeyManager;
import org.springframework.security.saml.metadata.ExtendedMetadata;
import org.springframework.security.saml.metadata.MetadataGenerator;
import org.springframework.security.saml.metadata.MetadataGeneratorFilter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${saml.sp}")
    private String samlAudience;

    @Autowired
    @Qualifier("saml")
    private SavedRequestAwareAuthenticationSuccessHandler samlAuthSuccessHandler;

    @Autowired
    @Qualifier("saml")
    private SimpleUrlAuthenticationFailureHandler samlAuthFailureHandler;

    @Autowired
    private SAMLEntryPoint samlEntryPoint;

    @Autowired
    private SAMLLogoutFilter samlLogoutFilter;

    @Autowired
    private SAMLLogoutProcessingFilter samlLogoutProcessingFilter;

    /**
     *  4.1 When the user tries to log in for the first time, the samlEntryPoint will handle the entry request.
     *  Then, the samlDiscovery bean (if enabled) will discover the IdP to contact for authentication.
     * @return
     */
    @Bean
    public SAMLDiscovery samlDiscovery() {
        SAMLDiscovery idpDiscovery = new SAMLDiscovery();
        return idpDiscovery;
    }

    @Autowired
    private SAMLAuthenticationProvider samlAuthenticationProvider;

    @Autowired
    private ExtendedMetadata extendedMetadata;

    @Autowired
    private KeyManager keyManager;

    /**
     * 6. we'll provide IdP metadata XML to the SP. It'll help to let our IdP know which SP endpoint
     *   it should redirect to once the user is logged in.
     *   we'll configure the MetadataGenerator bean to enable Spring SAML to handle the metadata:
     * @return
     */
    public MetadataGenerator metadataGenerator() {
        MetadataGenerator metadataGenerator = new MetadataGenerator();
        metadataGenerator.setEntityId(samlAudience);
        metadataGenerator.setExtendedMetadata(extendedMetadata);
        metadataGenerator.setIncludeDiscoveryExtension(false);
        metadataGenerator.setKeyManager(keyManager);
        return metadataGenerator;
    }

    /**
     * 4.2 Next, when the user logs in, the IdP redirects the SAML response to the /saml/sso URI for processing,
     * and corresponding samlWebSSOProcessingFilter will authenticate the associated auth token.
     * When successful, the successRedirectHandler will redirect the user to the default target URL (/home).
     * Otherwise, the authenticationFailureHandler will redirect the user to the /error URL
     * @return
     * @throws Exception
     */
    @Bean
    public SAMLProcessingFilter samlWebSSOProcessingFilter() throws Exception {
        SAMLProcessingFilter samlWebSSOProcessingFilter = new SAMLProcessingFilter();
        samlWebSSOProcessingFilter.setAuthenticationManager(authenticationManager());
        samlWebSSOProcessingFilter.setAuthenticationSuccessHandler(samlAuthSuccessHandler);
        samlWebSSOProcessingFilter.setAuthenticationFailureHandler(samlAuthFailureHandler);
        return samlWebSSOProcessingFilter;
    }

    /**
     * 3. let's create a few filters for our SAML URIs like /discovery, /login, and /logout:
     * 4. then we'll add a few corresponding filters and handlers:
     *  like SAMLProcessingFilter, SAMLDiscovery, SavedRequestAwareAuthenticationSuccessHandler,
     *       SimpleUrlAuthenticationFailureHandler
     * @return
     * @throws Exception
     */
    @Bean
    public FilterChainProxy samlFilter() throws Exception {
        List<SecurityFilterChain> chains = new ArrayList<>();
        chains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/SSO/**"),
                samlWebSSOProcessingFilter()));
        chains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/discovery/**"),
                samlDiscovery()));
        chains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/login/**"),
                samlEntryPoint));
        chains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/logout/**"),
                samlLogoutFilter));
        chains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/SingleLogout/**"),
                samlLogoutProcessingFilter));
        return new FilterChainProxy(chains);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     *  6.1 we'll provide IdP metadata XML to the SP. It'll help to let our IdP know which SP endpoint
     *  it should redirect to once the user is logged in.
     * @return
     */
    @Bean
    public MetadataGeneratorFilter metadataGeneratorFilter() {
        return new MetadataGeneratorFilter(metadataGenerator());
    }

    /**
     * 11. we'll configure a basic HTTP security using the already discussed samlEntryPoint and samlFilter:
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();

        http
                .httpBasic()
                .authenticationEntryPoint(samlEntryPoint);

        http
                .addFilterBefore(metadataGeneratorFilter(), ChannelProcessingFilter.class)
                .addFilterAfter(samlFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(samlFilter(), CsrfFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();

        http
                .logout()
                .addLogoutHandler((request, response, authentication) -> {
                    try {
                        response.sendRedirect("/saml/logout");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(samlAuthenticationProvider);
    }
}
