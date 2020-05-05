package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.entity.User;
import com.bsuir.defenestratio.exceptions.CannotBlockUserException;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.exceptions.UsernameDuplicateException;
import com.bsuir.defenestratio.jpa.UserRepository;
import com.bsuir.defenestratio.service.ProfileService;
import com.bsuir.defenestratio.service.UserService;
import com.bsuir.defenestratio.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private ProfileService profileService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ProfileService profileService,
                           BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.encoder = encoder;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Cannot find user with username %s", username));
        } else return UserUtils.buildUserDetails(user);
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

    @Override
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameDuplicateException(
                    String.format("Cannot register user with this username, %s", user.getUsername()));
        }
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public void unlockUser(User user) {
        user.setDisabled(false);
      userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
