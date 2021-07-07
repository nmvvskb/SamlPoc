package com.taxilla.SamlPoc.config;

import com.taxilla.SamlPoc.authentication.CustomSAMLAuthenticationProvider;
import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.util.resource.ResourceException;
import org.opensaml.xml.parse.StaticBasicParserPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.security.saml.*;
import org.springframework.security.saml.context.SAMLContextProviderImpl;
import org.springframework.security.saml.key.JKSKeyManager;
import org.springframework.security.saml.key.KeyManager;
import org.springframework.security.saml.log.SAMLDefaultLogger;
import org.springframework.security.saml.metadata.CachingMetadataManager;
import org.springframework.security.saml.metadata.ExtendedMetadata;
import org.springframework.security.saml.metadata.ExtendedMetadataDelegate;
import org.springframework.security.saml.processor.HTTPPostBinding;
import org.springframework.security.saml.processor.HTTPRedirectDeflateBinding;
import org.springframework.security.saml.processor.SAMLBinding;
import org.springframework.security.saml.processor.SAMLProcessorImpl;
import org.springframework.security.saml.util.VelocityFactory;
import org.springframework.security.saml.websso.*;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SamlSecurityConfig {


    @Value("${saml.keystore.location}")
    private String samlKeystoreLocation;

    @Value("${saml.keystore.password}")
    private String samlKeystorePassword;

    @Value("${saml.keystore.alias}")
    private String samlKeystoreAlias;

    @Value("${saml.idp}")
    private String defaultIdp;

    /**
     * 8. For XML parsing, we can use an instance of the StaticBasicParserPool class:
     * @return
     */
    @Bean(initMethod = "initialize")
    public StaticBasicParserPool parserPool() {
        return new StaticBasicParserPool();
    }

    /**
     * 8.1 For XML parsing, we can use an instance of the StaticBasicParserPool class:
     * @return
     */
//    @Bean(name = "parserPoolHolder")
//    public ParserPoolHolder parserPoolHolder() {
//        return new ParserPoolHolder();
//    }

    /**
     * 10 we should configure the CustomSAMLAuthenticationProvider as a bean in the SecurityConfig class:
     * @return
     */
    @Bean
    public SAMLAuthenticationProvider samlAuthenticationProvider() {
        return new CustomSAMLAuthenticationProvider();
    }

    @Bean
    public SAMLContextProviderImpl contextProvider() {
        return new SAMLContextProviderImpl();
    }

    @Bean
    public static SAMLBootstrap samlBootstrap() {
        return new SAMLBootstrap();
    }

    @Bean
    public SAMLDefaultLogger samlLogger() {
        return new SAMLDefaultLogger();
    }

    @Bean
    public WebSSOProfileConsumer webSSOprofileConsumer() {
        return new WebSSOProfileConsumerImpl();
    }

    @Bean
    @Qualifier("hokWebSSOprofileConsumer")
    public WebSSOProfileConsumerHoKImpl hokWebSSOProfileConsumer() {
        return new WebSSOProfileConsumerHoKImpl();
    }

    @Bean
    public WebSSOProfile webSSOprofile() {
        return new WebSSOProfileImpl();
    }

    @Bean
    public WebSSOProfileConsumerHoKImpl hokWebSSOProfile() {
        return new WebSSOProfileConsumerHoKImpl();
    }

    @Bean
    public WebSSOProfileECPImpl ecpProfile() {
        return new WebSSOProfileECPImpl();
    }

    @Bean
    public SingleLogoutProfile logoutProfile() {
        return new SingleLogoutProfileImpl();
    }

    /**
     * 6.3 The MetadataGenerator bean requires an instance of the KeyManager to encrypt the exchange between SP and IdP:
     * @return
     */
    @Bean
    public KeyManager keyManager() {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource storeFile = loader.getResource(samlKeystoreLocation);
        Map<String, String> passwords = new HashMap<>();
        passwords.put(samlKeystoreAlias, samlKeystorePassword);
        return new JKSKeyManager(storeFile, samlKeystorePassword, passwords, samlKeystoreAlias);
    }

    /**
     * 2. WebSSOProfileOptions bean allows us to set up parameters of the request sent from SP to IdP asking for user authentication.
     * @return
     */
    @Bean
    public WebSSOProfileOptions defaultWebSSOProfileOptions() {
        WebSSOProfileOptions webSSOProfileOptions = new WebSSOProfileOptions();
        webSSOProfileOptions.setIncludeScoping(false);
        return webSSOProfileOptions;
    }

    /**
     * 1. This SAMLEntryPoint class will work as an entry point of SAML authentication.
     * @return
     */
    @Bean
    public SAMLEntryPoint samlEntryPoint() {
        SAMLEntryPoint samlEntryPoint = new SAMLEntryPoint();
        samlEntryPoint.setDefaultProfileOptions(defaultWebSSOProfileOptions());
        return samlEntryPoint;
    }

    /**
     * 6.2 we'll provide IdP metadata XML to the SP. It'll help to let our IdP know which SP endpoint
     * it should redirect to once the user is logged in.
     * @return
     */
    @Bean
    public ExtendedMetadata extendedMetadata() {
        ExtendedMetadata extendedMetadata = new ExtendedMetadata();
        extendedMetadata.setIdpDiscoveryEnabled(false);
        extendedMetadata.setSignMetadata(false);
        return extendedMetadata;
    }

    /**
     *  7. we'll configure the IdP metadata into our Spring Boot application using the ExtendedMetadataDelegate instance:
     * @return
     * @throws MetadataProviderException
     */
    @Bean
    @Qualifier("okta")
    public ExtendedMetadataDelegate oktaExtendedMetadataProvider() throws MetadataProviderException {
        File metadata = null;
        try {
            metadata = new File("./src/main/resources/saml/metadata/sso.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        FilesystemMetadataProvider provider = new FilesystemMetadataProvider(metadata);
        provider.setParserPool(parserPool());
        return new ExtendedMetadataDelegate(provider, extendedMetadata());
    }

    /**
     * 7.1  we'll configure the IdP metadata into our Spring Boot application
     * we've parsed the metadata from the sso.xml file that contains the IdP metadata XML,
     * copied from the Okta developer account while viewing the setup instructions.
     *
     * Similarly, the defaultIdp variable contains the IdP Issuer URL, copied from the Okta developer account.
     * @return
     * @throws MetadataProviderException
     * @throws ResourceException
     */
    @Bean
    @Qualifier("metadata")
    public CachingMetadataManager metadata() throws MetadataProviderException, ResourceException {
        List<MetadataProvider> providers = new ArrayList<>();
        providers.add(oktaExtendedMetadataProvider());
        CachingMetadataManager metadataManager = new CachingMetadataManager(providers);
        metadataManager.setDefaultIDP(defaultIdp);
        return metadataManager;
    }

    @Bean
    @Qualifier("saml")
    public SavedRequestAwareAuthenticationSuccessHandler successRedirectHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successRedirectHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successRedirectHandler.setDefaultTargetUrl("/home");
        return successRedirectHandler;
    }

    @Bean
    @Qualifier("saml")
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
        failureHandler.setUseForward(true);
        failureHandler.setDefaultFailureUrl("/error");
        return failureHandler;
    }

    /**
     *  5. let's add the logout handlers for single and global logouts:
     *  those are SimpleUrlLogoutSuccessHandler, SecurityContextLogoutHandler, SAMLLogoutProcessingFilter,
     *   SAMLLogoutFilter
     * @return
     */
    @Bean
    public SimpleUrlLogoutSuccessHandler successLogoutHandler() {
        SimpleUrlLogoutSuccessHandler successLogoutHandler = new SimpleUrlLogoutSuccessHandler();
        successLogoutHandler.setDefaultTargetUrl("/");
        return successLogoutHandler;
    }

    @Bean
    public SecurityContextLogoutHandler logoutHandler() {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setInvalidateHttpSession(true);
        logoutHandler.setClearAuthentication(true);
        return logoutHandler;
    }

    @Bean
    public SAMLLogoutProcessingFilter samlLogoutProcessingFilter() {
        return new SAMLLogoutProcessingFilter(successLogoutHandler(), logoutHandler());
    }

    @Bean
    public SAMLLogoutFilter samlLogoutFilter() {
        return new SAMLLogoutFilter(successLogoutHandler(),
                new LogoutHandler[] { logoutHandler() },
                new LogoutHandler[] { logoutHandler() });
    }

    @Bean
    public HTTPPostBinding httpPostBinding() {
        return new HTTPPostBinding(parserPool(), VelocityFactory.getEngine());
    }

    @Bean
    public HTTPRedirectDeflateBinding httpRedirectDeflateBinding() {
        return new HTTPRedirectDeflateBinding(parserPool());
    }

    /**
     *  9. we require a processor to parse the SAML message from the HTTP request:
     *   we've used POST and Redirect bindings with respect to our configuration in the Okta developer account.
     * @return
     */
    @Bean
    public SAMLProcessorImpl processor() {
        ArrayList<SAMLBinding> bindings = new ArrayList<>();
        bindings.add(httpRedirectDeflateBinding());
        bindings.add(httpPostBinding());
        return new SAMLProcessorImpl(bindings);
    }
}
