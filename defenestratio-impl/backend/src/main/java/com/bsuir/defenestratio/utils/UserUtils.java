package com.bsuir.defenestratio.utils;

import com.bsuir.defenestratio.entity.Role;
import com.bsuir.defenestratio.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserUtils {
    public static Collection<GrantedAuthority> buildAuthority(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

    public static UserDetails buildUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), UserUtils.buildAuthority(user.getRole()));
    }
}
