package com.pbuczek.TaskForUJ.entity;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String url;

    public ServiceEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public ServiceEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public void updateFrom(ServiceEntity source) {
        this.url=source.url;
    }
}
