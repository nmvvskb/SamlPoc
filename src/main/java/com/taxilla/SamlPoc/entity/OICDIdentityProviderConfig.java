package com.taxilla.SamlPoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;


public class OICDIdentityProviderConfig {

    private String providerDescription;

    private Boolean addShadowUserOnLogin;

    private Boolean skipSslValidation;

    private String authUrl;

    private String tokenUrl;

    private String tokenKeyUrl;

    private String userInfoUrl;

    private String tokenKey;

    private String relyingPartyId;

    private String[] scopes;

    private Object checkTokenUrl;

    private String responseType;

    private Boolean clientAuthInBody;

    private String issuer;

    private String userPropagationParameter;

    private String relyingPartySecret;

    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public Boolean getAddShadowUserOnLogin() {
        return addShadowUserOnLogin == null ? true : addShadowUserOnLogin;
    }

    public void setAddShadowUserOnLogin(Boolean addShadowUserOnLogin) {
        this.addShadowUserOnLogin = addShadowUserOnLogin;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getTokenKeyUrl() {
        return tokenKeyUrl;
    }

    public void setTokenKeyUrl(String tokenKeyUrl) {
        this.tokenKeyUrl = tokenKeyUrl;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getRelyingPartyId() {
        return relyingPartyId;
    }

    public void setRelyingPartyId(String relyingPartyId) {
        this.relyingPartyId = relyingPartyId;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public Object getCheckTokenUrl() {
        return checkTokenUrl;
    }

    public void setCheckTokenUrl(Object checkTokenUrl) {
        this.checkTokenUrl = checkTokenUrl;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Boolean getClientAuthInBody() {
        return clientAuthInBody;
    }

    public void setClientAuthInBody(Boolean clientAuthInBody) {
        this.clientAuthInBody = clientAuthInBody;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getUserPropagationParameter() {
        return userPropagationParameter;
    }

    public void setUserPropagationParameter(String userPropagationParameter) {
        this.userPropagationParameter = userPropagationParameter;
    }

    public String getRelyingPartySecret() {
        return relyingPartySecret;
    }

    public void setRelyingPartySecret(String relyingPartySecret) {
        this.relyingPartySecret = relyingPartySecret;
    }

    public Boolean isSkipSslValidation() {
        return skipSslValidation == null ? true : skipSslValidation;
    }

    public void setSkipSslValidation(Boolean skipSslValidation) {
        this.skipSslValidation = skipSslValidation;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OICDIdentityProviderConfig that = (OICDIdentityProviderConfig) o;
        return Objects.equals(providerDescription, that.providerDescription) &&
                Objects.equals(addShadowUserOnLogin, that.addShadowUserOnLogin) &&
                Objects.equals(skipSslValidation, that.skipSslValidation) &&
                Objects.equals(authUrl, that.authUrl) &&
                Objects.equals(tokenUrl, that.tokenUrl) &&
                Objects.equals(tokenKeyUrl, that.tokenKeyUrl) &&
                Objects.equals(userInfoUrl, that.userInfoUrl) &&
                Objects.equals(tokenKey, that.tokenKey) &&
                Objects.equals(relyingPartyId, that.relyingPartyId) &&
                Arrays.equals(scopes, that.scopes) &&
                Objects.equals(checkTokenUrl, that.checkTokenUrl) &&
                Objects.equals(responseType, that.responseType) &&
                Objects.equals(clientAuthInBody, that.clientAuthInBody) &&
                Objects.equals(issuer, that.issuer) &&
                Objects.equals(userPropagationParameter, that.userPropagationParameter) &&
                Objects.equals(relyingPartySecret, that.relyingPartySecret);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(providerDescription, addShadowUserOnLogin, skipSslValidation, authUrl, tokenUrl, tokenKeyUrl, userInfoUrl, tokenKey, relyingPartyId, checkTokenUrl, responseType, clientAuthInBody, issuer, userPropagationParameter, relyingPartySecret);
        result = 31 * result + Arrays.hashCode(scopes);
        return result;
    }

    @Override
    public String toString() {
        return "OICDIdentityProviderConfig{" +
                "providerDescription='" + providerDescription + '\'' +
                ", addShadowUserOnLogin=" + addShadowUserOnLogin +
                ", skipSslValidation=" + skipSslValidation +
                ", authUrl='" + authUrl + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", tokenKeyUrl='" + tokenKeyUrl + '\'' +
                ", userInfoUrl='" + userInfoUrl + '\'' +
                ", tokenKey='" + tokenKey + '\'' +
                ", relyingPartyId='" + relyingPartyId + '\'' +
                ", scopes=" + Arrays.toString(scopes) +
                ", checkTokenUrl=" + checkTokenUrl +
                ", responseType='" + responseType + '\'' +
                ", clientAuthInBody=" + clientAuthInBody +
                ", issuer='" + issuer + '\'' +
                ", userPropagationParameter='" + userPropagationParameter + '\'' +
                ", relyingPartySecret='" + relyingPartySecret + '\'' +
                '}';
    }
}
