package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.entity.User;

import java.util.List;

public interface UserService {

    User findUser(Long userId);

    User findUserByUsername(String username);

    List<User> findAll();

    Profile findUserProfile(Long userId);

    void updateUserProfile(Profile profile);

    void disable(Long userId);

    void unlockUser(User user);

    User save(User user);
}
