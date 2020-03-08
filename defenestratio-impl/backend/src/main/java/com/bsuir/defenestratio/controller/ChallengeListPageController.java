package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/challengeList")
public class ChallengeListPageController {

    private ChallengeService challengeService;

    @Autowired
    public ChallengeListPageController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public List<Challenge> showChallengeList() {
        return challengeService.findAll(0, 10);
    }
}
