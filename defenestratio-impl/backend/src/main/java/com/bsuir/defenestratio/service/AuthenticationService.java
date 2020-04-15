package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.User;

public interface AuthenticationService {
    User login(User user);

    User register(User user);
}
