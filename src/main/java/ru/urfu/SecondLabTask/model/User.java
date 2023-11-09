package ru.urfu.SecondLabTask.model;

import java.util.HashSet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ElementCollection;
import java.util.Set;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "my_users")
public class User
{
    @Id
    @GeneratedValue
    private Long id;
    private String password;
    private String userName;
    private Boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") })
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
        this.roles = new HashSet<Role>();
    }
}