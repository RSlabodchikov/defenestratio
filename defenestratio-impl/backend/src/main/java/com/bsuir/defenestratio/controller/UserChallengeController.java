package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.entity.UserChallenge;
import com.bsuir.defenestratio.service.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/userChallenge")
public class UserChallengeController {

    private UserChallengeService userChallengeService;

    @Autowired
    public UserChallengeController(UserChallengeService userChallengeService) {
        this.userChallengeService = userChallengeService;
    }

    @GetMapping
    public UserChallenge getUserChallengeByUserIdAndChallengeId(UserChallenge userChallenge) {
        return userChallengeService.findUserChallengeByUserIdAndChallengeId(
                userChallenge.getUserId(), userChallenge.getChallengeId());
    }

    @GetMapping
    public List<UserChallenge> getUserChallengesByUserId(UserChallenge userChallenge) {
        return userChallengeService.findAllUserChallengesByUserId(userChallenge.getUserId());
    }
}
