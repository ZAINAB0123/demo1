package com.example.demo.security;

import com.example.demo.entity.user.User;
import com.example.demo.entity.user.UserStatus;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetails {
    private final User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+ user.getRole().name());
     List<SimpleGrantedAuthority>list =  new ArrayList<>();
     list.add(simpleGrantedAuthority);
       return list;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isEnabled() {
    if (user.getStatus() == UserStatus.ACTIVE) {
        return true;
    }else {
        return false;
    }
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;

    }
    @Override
    public boolean isAccountNonLocked() {
    return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
    return  true;
    }
}
