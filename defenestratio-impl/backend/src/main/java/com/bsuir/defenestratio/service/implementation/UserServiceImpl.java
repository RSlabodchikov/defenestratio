package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.entity.User;
import com.bsuir.defenestratio.exceptions.CannotBlockUserException;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.UserRepository;
import com.bsuir.defenestratio.service.ProfileService;
import com.bsuir.defenestratio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ProfileService profileService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    @Override
    public void disable(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format("User with id %s not found", userId)));
        if (user.getDisabled()) {
            throw new CannotBlockUserException("User with id %s already disabled");
        }
        user.setDisabled(true);
        userRepository.save(user);
    }

    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format("User with id %s not found", userId)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Profile findUserProfile(Long userId) {
        return profileService.findProfile(userId);
    }

    @Override
    public void updateUserProfile(Profile profile) {
        profileService.updateProfile(profile);
    }
}
