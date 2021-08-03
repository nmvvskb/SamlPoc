package com.taxilla.SamlPoc.validation;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;

import java.net.URL;

public interface IIdpValidation {

    boolean validateIdp(IdentityProviderDetailDto providerDetail);

    public static boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
