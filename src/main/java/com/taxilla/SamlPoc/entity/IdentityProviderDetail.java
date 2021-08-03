package com.taxilla.SamlPoc.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxilla.SamlPoc.utils.OicdIdpConfigJsonConverter;
import com.taxilla.SamlPoc.utils.SamlConstants;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "um_identity_provider")
public class IdentityProviderDetail extends DomainObjectWithHexId {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "ORIGIN_KEY")
    private String originKey;

    @Column(name = "ORG_ID")
    private String organizationId;

    @Column(name = "IDENTITY_ZONE_ID")
    private String identityZoneId;

    @Column(name = "CONFIG")
    private String config;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config=config;
    }

    public String getIdentityZoneId() {
        return identityZoneId;
    }

    public void setIdentityZoneId(String identityZoneId) {
        this.identityZoneId = identityZoneId;
    }


    @Override
    public String toString() {
        return "IdentityProviderDetail{" +
                "id='" + id + '\'' +
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
