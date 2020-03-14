package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.User;
import com.bsuir.defenestratio.jpa.UserRepository;
import com.bsuir.defenestratio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
