package com.taxilla.SamlPoc.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.saml.SAMLAuthenticationProvider;
import org.springframework.security.saml.SAMLCredential;

public class CustomSAMLAuthenticationProvider extends SAMLAuthenticationProvider {

    /**
     * 10.1 Last, we require a custom implementation of the SAMLAuthenticationProvider class to check the instance
     * of the ExpiringUsernameAuthenticationToken class and set the obtained authorities:
     * @param credential
     * @param userDetail
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getEntitlements(SAMLCredential credential, Object userDetail) {

        if(userDetail instanceof ExpiringUsernameAuthenticationToken) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.addAll(((ExpiringUsernameAuthenticationToken) userDetail).getAuthorities());
            return authorities;

        } else {
            return Collections.emptyList();
        }
    }

}
