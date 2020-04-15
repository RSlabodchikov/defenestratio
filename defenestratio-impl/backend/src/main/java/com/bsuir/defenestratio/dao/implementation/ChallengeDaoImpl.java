package com.bsuir.defenestratio.dao.implementation;

import com.bsuir.defenestratio.dao.ChallengeDao;
import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.entity.UserChallenge;
import com.bsuir.defenestratio.jpa.ChallengeRepository;
import com.bsuir.defenestratio.jpa.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeDaoImpl implements ChallengeDao {

    private ChallengeRepository challengeRepository;
    private UserChallengeRepository userChallengeRepository;

    @Autowired
    public ChallengeDaoImpl(ChallengeRepository challengeRepository, UserChallengeRepository userChallengeRepository) {
        this.challengeRepository = challengeRepository;
        this.userChallengeRepository = userChallengeRepository;
    }

    @Override
    public List<Challenge> findAllChallengesAvailableForUser(Long userId) {
        List<Challenge> challenges = challengeRepository.findAll();
        List<UserChallenge> userChallenges = userChallengeRepository.findAllByUserId(userId);

        userChallenges.forEach(userChallenge -> challenges.removeIf(challenge -> challenge.getId().equals(userChallenge.getChallengeId())));
        return challenges;
    }
}
