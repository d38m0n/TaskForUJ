package com.pbuczek.TaskForUJ.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pbuczek.TaskForUJ.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String login;
    @JsonIgnore
    private String password;
    private boolean active;
    @OneToMany()
    @JoinColumn(name = "logbook_id")
    private Set<LogbookEntity> logbook;

    @ManyToMany()
    @JoinColumn(name = "services_id")
    private Set<ServiceEntity> service;

    public UserEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }


    public Set<LogbookEntity> getLogbook() {
        return logbook;
    }

    public Set<ServiceEntity> getService() {
        return service;
    }


    public void unlocked() {
        this.active = true;
    }

    public void locked() {
        this.active = false;
    }

    public void updateFrom(final UserEntity source) {
        this.login = source.login;
        this.active = source.active;
    }

    public void addServiceAccess(ServiceEntity source) {
        this.service.add(source);
    }

    public UserEntity deleteServiceAccess(ServiceEntity source) {
        service.remove(source);
        return this;
    }


    public boolean authorize(UserDTO source, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(source.getPassword(), this.password) &&
                this.login.equals(source.getLogin());
    }

    public UserEntity addAuthorizationDate(LogbookEntity logbookEntity) {
        this.logbook.add(logbookEntity);
        return this;
    }

    public boolean changePassword(UserDTO source, PasswordEncoder passwordEncoder) {
        if (authorize(source, passwordEncoder)) {
            password = passwordEncoder.encode(source.getNewPassword());
            return true;
        } else {
            return false;
        }
    }

    public boolean findService(String url) {
        boolean flag = false;
        for (ServiceEntity serviceEntity : service) {
            if (serviceEntity.getUrl().equals(url)) flag = true;
        }
        return flag;
    }
}
