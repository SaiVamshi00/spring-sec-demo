package com.telusko.spring_sec_demo.entity;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import tools.jackson.databind.module.SimpleAbstractTypeResolver;

@Entity 
public class LoginEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    private String role;

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public Long getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String userName) {
        this.userName = userName;
    }
     public String getUsername() {
        return userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LoginEntity(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    private boolean enabled = true;

    public LoginEntity() {
    }
     public LoginEntity(String username, String password, String role) {
        this.userName = username;
        this.password = password;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
            return List.of(new SimpleGrantedAuthority("ROLE_"));
       
    }
   

}
