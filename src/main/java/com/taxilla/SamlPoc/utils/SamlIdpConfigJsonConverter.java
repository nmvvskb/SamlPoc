package com.taxilla.SamlPoc.utils;

import com.taxilla.SamlPoc.entity.SAMLIdentityProviderConfig;

public class SamlIdpConfigJsonConverter implements HashMapConverter<SAMLIdentityProviderConfig, String> {

private static final SAMLIdentityProviderConfig identityProviderConfig = new SAMLIdentityProviderConfig();

@Override
public SAMLIdentityProviderConfig getInstance() {
        return identityProviderConfig;
        }
}
