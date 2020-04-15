package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.dao.ChallengeDao;
import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/{roleId}/challenges")
public class ChallengeController {

    private ChallengeService challengeService;
    private ChallengeDao challengeDao;

    @Autowired
    public ChallengeController(ChallengeService challengeService, ChallengeDao challengeDao) {
        this.challengeService = challengeService;
        this.challengeDao = challengeDao;
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Challenge> challenges = challengeService.findAllChallenges();
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity findAllAvailableForUser(@RequestParam Long userId) {
        List<Challenge> challenges = challengeDao.findAllChallengesAvailableForUser(userId);
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }

    @GetMapping(value = "/{challengeId}")
    public ResponseEntity findById(@PathVariable(name = "challengeId") Long challengeId) {
        return new ResponseEntity<>(
                challengeService.findChallengeById(challengeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createChallenge(@RequestBody Challenge challenge) {
        return new ResponseEntity<>(
                challengeService.createChallenge(challenge), HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity updateChallenge(@RequestBody Challenge challenge) {
        challengeService.updateChallenge(challenge);
        return new ResponseEntity<>(
                "Challenge updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{challengeId}")
    public ResponseEntity deleteChallenge(@PathVariable(name = "challengeId") Long challengeId) {
        challengeService.deleteChallenge(challengeId);
        return new ResponseEntity<>(
                "Challenge deleted successfully", HttpStatus.NO_CONTENT);
    }
}
