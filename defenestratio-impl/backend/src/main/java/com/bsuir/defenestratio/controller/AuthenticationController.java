package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.entity.User;
import com.bsuir.defenestratio.service.AuthenticationService;
import com.bsuir.defenestratio.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JwtTokenUtils jwtTokenUtils) {
        this.authenticationService = authenticationService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping(value = "/login")
    public User authenticateUser(HttpServletResponse response, @RequestBody User user) {
        User authenticatedUser = authenticationService.login(user);
        String token = jwtTokenUtils.generateToken(authenticatedUser);
        response.addHeader("X-Auth-Token", token);
        return authenticatedUser;
    }

    @PostMapping(value = "/register")
    public User registerNewUser(HttpServletResponse response, @RequestBody User user) {
        User createdUser = authenticationService.register(user);
        String token = jwtTokenUtils.generateToken(user);
        response.addHeader("X-Auth-Token", token);
        return createdUser;
    }
}
