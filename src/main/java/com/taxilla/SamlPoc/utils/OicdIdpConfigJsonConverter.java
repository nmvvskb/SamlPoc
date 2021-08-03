package com.taxilla.SamlPoc.utils;

import com.taxilla.SamlPoc.entity.OICDIdentityProviderConfig;

public class OicdIdpConfigJsonConverter implements HashMapConverter<OICDIdentityProviderConfig, String> {

    private static final OICDIdentityProviderConfig identityProviderConfig = new OICDIdentityProviderConfig();

    @Override
    public OICDIdentityProviderConfig getInstance() {
        return identityProviderConfig;
    }
}
