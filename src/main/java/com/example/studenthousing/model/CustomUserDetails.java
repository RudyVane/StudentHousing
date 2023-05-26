package com.example.studenthousing.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails() {
    }

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        System.out.println(this.username + " account non expired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        System.out.println(this.username + " account non locked");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        System.out.println(this.username + " credentials non expired");
        return true;
    }

    @Override
    public boolean isEnabled() {
        System.out.println(this.username + " is enabled");
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return a collection of authorities (roles) associated with the user
        // For the basic form, return a single authority (role): "ROLE_USER"
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
