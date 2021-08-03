package com.taxilla.SamlPoc.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class DomainObjectWithHexId implements Serializable {

    private static final long serialVersionUID = 1L;


//    @Id
//    @Column(name = "ID")
//    @NotNull
//    private String id;

    @Column(name = "CREATED_BY_USER_ID")
    private String createdByUserId;

    @Column(name = "CREATED_DATE", updatable= false)
    private Date createdDate;

    @Column(name = "LAST_MODIFIED_BY_USER_ID")
    private String lastModifiedByUserId;

    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

//    public DomainObjectWithHexId() {
//        this.id = UUID.randomUUID().toString();
//    }
//
//    public  String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    public void setLastModifiedByUserId(String lastModifiedByUserId) {
        this.lastModifiedByUserId = lastModifiedByUserId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @PreUpdate
    public void updateTimeStamps() {
        lastModifiedDate = new Date();
    }

    @PrePersist
    public void createTimeStamp(){
        createdDate = new Date();
    }

    @Override
    public String toString() {
        return "DomainObjectWithHexId{" +
//                "id='" + id + '\'' +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedByUserId='" + lastModifiedByUserId + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
