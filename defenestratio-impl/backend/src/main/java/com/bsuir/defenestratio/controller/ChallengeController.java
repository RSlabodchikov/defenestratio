package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.service.ChallengeService;
import com.bsuir.defenestratio.service.RoleService;
import com.bsuir.defenestratio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/challenge")
public class ChallengeController {

    private ChallengeService challengeService;

    private UserService userService;

    private RoleService roleService;

    private Integer adminRole = 1;

    @Autowired
    public ChallengeController(ChallengeService challengeService, UserService userService, RoleService roleService) {
        this.challengeService = challengeService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public Challenge getChallengeById(@PathVariable Long id) {
        return challengeService.findChallengeById(id);
    }

    @PostMapping("/{userId}/save")
    public void saveChallenge(@PathVariable Long userId, Challenge challenge) {
        if (userService.findUserById(userId).getRole() == roleService.findRoleById(adminRole)) {
            challengeService.saveChallenge(challenge);
        }
    }

    @PostMapping("/{userId}/delete")
    public void deleteChallengeById (@PathVariable Long userId, Long challengeId) {
        if (userService.findUserById(userId).getRole() == roleService.findRoleById(adminRole)) {
                challengeService.deleteChallengeById(challengeId);
        }
    }
}
