package com.taxilla.SamlPoc.utils;

public class SamlConstants {

    //okta
    public final static String saml_okta_idp_entity_id = "http://www.okta.com/exk75btfXVhh0TSqe695";
    public final static String saml_okta_idp_sso_url = "https://taxilla4.okta.com/app/taxilla4_samplesamlwithspringandocta_1/exk75btfXVhh0TSqe695/sso/saml";
    public final static String saml_okta_metadata_file_path = "./src/main/resources/saml/metadata/okta.xml";

    //Auth0
    public final static String saml_autho_idp_entity_id = "urn:taxilla1.jp.auth0.com";
    public final static String saml_autho_idp_sso_url = "https://taxilla1.jp.auth0.com/samlp/J34wAsYSMxFQC8qaaXKZ4DrvGJSHIWMI";
    public final static String saml_autho_metadata_file_path = "./src/main/resources/saml/metadata/taxilla1_jp_auth0_com-metadata.xml";

    //SSOCirle
    public final static String saml_ssocircle_idp_entity_id = "https://idp.ssocircle.com";
    public final static String saml_ssocircle_idp_sso_url = "https://idp.ssocircle.com:443/sso/SSOPOST/metaAlias/publicidp";
    public final static String saml_ssocircle_metadata_file_path = "./src/main/resources/saml/metadata/ssoCircle.xml";

    public final static String APPENDER ="?";


    public static final String ORIGIN = "origin";
    public static final String UAA = "uaa";
    public static final String LOGIN_SERVER = "login-server";
    public static final String LDAP = "ldap";
    public static final String KEYSTONE = "keystone";
    public static final String SAML = "saml";
    public static final String OAUTH20 = "oauth2.0";
    public static final String OIDC10 = "oidc1.0";
    public static final String NotANumber = "NaN";
    public static final String UNKNOWN = "unknown";

}
