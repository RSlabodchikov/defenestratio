package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.Challenge;

import java.util.List;

public interface ChallengeService {

    void saveChallenge(Challenge challenge);

    List<Challenge> findAllChallenges();

    Challenge findChallengeById(Long challengeId);

    void deleteChallengeById(Long challengeId);

    List<Challenge> findAll(int offset, int limit);

}
