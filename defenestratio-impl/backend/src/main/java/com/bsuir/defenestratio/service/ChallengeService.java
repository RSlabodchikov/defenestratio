package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.Challenge;

import java.util.List;

public interface ChallengeService {

    Challenge createChallenge(Challenge challenge);

    void updateChallenge(Challenge challenge);

    List<Challenge> findAllChallenges();

    Challenge findChallengeById(Long challengeId);

    void deleteChallenge(Long challengeId);
}
