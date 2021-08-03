package com.taxilla.SamlPoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class SAMLIdentityProviderConfig {

    public enum ExternalGroupMappingMode {
        EXPLICITLY_MAPPED,
        AS_SCOPES
    }


    /*
    List of email domains associated with the provider for the purpose of associating users to the correct origin
     upon invitation. If empty list, no invitations are accepted. Wildcards supported.
     */
    private String[] emailDomain;

    private String providerDescription;
    private String[] externalGroupsWhitelist;

    /*
    Map external attribute to UAA recognized mappings.
     */
    private Map<String, Object> attributeMappings = new HashMap<>();
//    private AttributeMappings attributeMappings;

    /*Determines whether users should be allowed to authenticate without having a user pre-populated in the users
    database (if true), or whether shadow users must be created before login by an administrator (if false).
    defaults to true
    */
    private boolean addShadowUserOnLogin;

    /*
    Set to true, to store custom user attributes to be fetched from the /userinfo endpoint
    defaults to true
     */
    private boolean storeCustomAttributes;

    /*
    SAML Metadata - either an XML string or a URL that will deliver XML content
     */
    private String metaDataLocation;

    /*
    The name ID to use for the username, default is "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified".
     */
    private String nameID;

    /*
    SAML assertion consumer index, default is 0
     */
    private int assertionConsumerIndex;

    /*
    Should metadata be validated, defaults to false
     */
    private boolean metadataTrustCheck;

    /*
    Should the SAML login link be displayed on the login page, defaults to false
     */
    private boolean showSamlLink;

    /*
    The link text for the SAML IDP on the login page
    NOTE : Required if the showSamlLink is set to true
     */
    private String linkText;

    private String iconUrl;

    /*
    Either EXPLICITLY_MAPPED in order to map external groups to OAuth scopes using the group mappings,
    or AS_SCOPES to use SAML group names as scopes.
    Optional (defaults to "EXPLICITLY_MAPPED")
     */
    private ExternalGroupMappingMode groupMappingMode = ExternalGroupMappingMode.EXPLICITLY_MAPPED;

    /*
    Set to true, to skip SSL validation when fetching metadata.
    default to false
     */
    private boolean skipSslValidation;

    /*
    List of AuthnContextClassRef to include in the SAMLRequest. If not specified no AuthnContext will be requested.
     */
    private String[] authnContext;

    /*
    Property is deprecated and value is ignored.
     */
    private String socketFactoryClassName;

    private String idpEntityAlias;

    private String zoneId;

    public String[] getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String[] emailDomain) {
        this.emailDomain = emailDomain;
    }

    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public String[] getExternalGroupsWhitelist() {
        return externalGroupsWhitelist;
    }

    public void setExternalGroupsWhitelist(String[] externalGroupsWhitelist) {
        this.externalGroupsWhitelist = externalGroupsWhitelist;
    }

    public Map<String, Object> getAttributeMappings() {
        return attributeMappings;
    }

    public void setAttributeMappings(Map<String, Object> attributeMappings) {
        this.attributeMappings = attributeMappings;
    }

    public boolean isAddShadowUserOnLogin() {
        return addShadowUserOnLogin;
    }

    public void setAddShadowUserOnLogin(boolean addShadowUserOnLogin) {
        this.addShadowUserOnLogin = addShadowUserOnLogin;
    }

    public boolean isStoreCustomAttributes() {
        return storeCustomAttributes;
    }

    public void setStoreCustomAttributes(boolean storeCustomAttributes) {
        this.storeCustomAttributes = storeCustomAttributes;
    }

    public String getMetaDataLocation() {
        return metaDataLocation;
    }

    public void setMetaDataLocation(String metaDataLocation) {
        this.metaDataLocation = metaDataLocation;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public int getAssertionConsumerIndex() {
        return assertionConsumerIndex;
    }

    public void setAssertionConsumerIndex(int assertionConsumerIndex) {
        this.assertionConsumerIndex = assertionConsumerIndex;
    }

    public boolean isMetadataTrustCheck() {
        return metadataTrustCheck;
    }

    public void setMetadataTrustCheck(boolean metadataTrustCheck) {
        this.metadataTrustCheck = metadataTrustCheck;
    }

    public boolean isShowSamlLink() {
        return showSamlLink;
    }

    public void setShowSamlLink(boolean showSamlLink) {
        this.showSamlLink = showSamlLink;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public ExternalGroupMappingMode getGroupMappingMode() {
        return groupMappingMode;
    }

    public void setGroupMappingMode(ExternalGroupMappingMode groupMappingMode) {
        this.groupMappingMode = groupMappingMode;
    }

    public boolean isSkipSslValidation() {
        return skipSslValidation;
    }

    public void setSkipSslValidation(boolean skipSslValidation) {
        this.skipSslValidation = skipSslValidation;
    }

    public String[] getAuthnContext() {
        return authnContext;
    }

    public void setAuthnContext(String[] authnContext) {
        this.authnContext = authnContext;
    }

    public String getSocketFactoryClassName() {
        return socketFactoryClassName;
    }

    public void setSocketFactoryClassName(String socketFactoryClassName) {
        this.socketFactoryClassName = socketFactoryClassName;
    }

    public String getIdpEntityAlias() {
        return idpEntityAlias;
    }

    public void setIdpEntityAlias(String idpEntityAlias) {
        this.idpEntityAlias = idpEntityAlias;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public SAMLIdentityProviderConfig() {
        super();
    }

    //Need to discuss about override methods
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "SAMLIdentityProviderConfig{" +
                "emailDomain=" + Arrays.toString(emailDomain) +
                ", providerDescription='" + providerDescription + '\'' +
                ", externalGroupsWhitelist=" + Arrays.toString(externalGroupsWhitelist) +
                ", attributeMappings=" + attributeMappings +
                ", addShadowUserOnLogin=" + addShadowUserOnLogin +
                ", storeCustomAttributes=" + storeCustomAttributes +
                ", metaDataLocation='" + metaDataLocation + '\'' +
                ", nameID='" + nameID + '\'' +
                ", assertionConsumerIndex=" + assertionConsumerIndex +
                ", metadataTrustCheck=" + metadataTrustCheck +
                ", showSamlLink=" + showSamlLink +
                ", linkText='" + linkText + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", groupMappingMode=" + groupMappingMode +
                ", skipSslValidation=" + skipSslValidation +
                ", authnContext=" + Arrays.toString(authnContext) +
                ", socketFactoryClassName='" + socketFactoryClassName + '\'' +
                ", idpEntityAlias='" + idpEntityAlias + '\'' +
                ", zoneId='" + zoneId + '\'' +
                '}';
    }

}
