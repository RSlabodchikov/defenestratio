package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.entity.Role;
import com.bsuir.defenestratio.entity.User;
import com.bsuir.defenestratio.service.AuthenticationService;
import com.bsuir.defenestratio.service.UserService;
import com.bsuir.defenestratio.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public User login(User user) {
        User foundUser = userService.findUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                UserUtils.buildUserDetails(foundUser), user.getPassword(), UserUtils.buildAuthority(foundUser.getRole())
        );

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return foundUser;

    }

    @Override
    public User register(User user) {
        user.setRole(Role.CLIENT);
        user.setDisabled(false);
        user.setProfile(new Profile());
        User registeredUser = userService.save(user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                registeredUser, user.getPassword(), UserUtils.buildAuthority(registeredUser.getRole())
        );

        if (authenticationToken.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return registeredUser;
    }
}
