package com.taxilla.SamlPoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxilla.SamlPoc.utils.SamlConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class IdentityProviderDetailDto<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String type;

    private Boolean active;

    private String originKey;

    private String organizationId;

    private String identityZoneId;

    private T config;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOriginKey() {
        return originKey;
    }

    public void setOriginKey(String originKey) {
        this.originKey = originKey;
        if (config != null && config instanceof SAMLIdentityProviderConfig) {
            ((SAMLIdentityProviderConfig) config).setIdpEntityAlias(originKey);
        }
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getIdentityZoneId() {
        return identityZoneId;
    }

    public void setIdentityZoneId(String identityZoneId) {
        this.identityZoneId = identityZoneId;
    }

    public T getConfig() {
        return config;
    }

    public void setConfig(T config) {
        ObjectMapper objectMapper =null;
        String strConfig = null;
        if (config == null) {
            this.type = SamlConstants.UNKNOWN;
        } else {
            try {
                objectMapper = new ObjectMapper();
                strConfig = objectMapper.writeValueAsString(config);
                if (strConfig != null) {
                    if (this.type.equalsIgnoreCase(SamlConstants.SAML)) {
                        SAMLIdentityProviderConfig samlConfig = objectMapper.readValue(strConfig,SAMLIdentityProviderConfig.class);
                        if (StringUtils.hasText(getOriginKey())) {
                            samlConfig.setIdpEntityAlias(getOriginKey());
                        }
                        if (StringUtils.hasText(getIdentityZoneId())) {
                            samlConfig.setZoneId(getIdentityZoneId());
                        }
                        this.config =(T)samlConfig;
                    } else if (this.type.equalsIgnoreCase(SamlConstants.OIDC10) || this.type.equalsIgnoreCase(SamlConstants.OAUTH20)) {
                        OICDIdentityProviderConfig oicdConfig = objectMapper.readValue(strConfig,OICDIdentityProviderConfig.class);
                        this.config =(T)oicdConfig;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                objectMapper =null;
                strConfig = null;
            }
        }
//        this.config = config;
    }

    @Override
    public String toString() {
        return "IdentityProviderDetailDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", originKey='" + originKey + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", identityZoneId='" + identityZoneId + '\'' +
                ", config=" + config +
                '}';
    }
}
