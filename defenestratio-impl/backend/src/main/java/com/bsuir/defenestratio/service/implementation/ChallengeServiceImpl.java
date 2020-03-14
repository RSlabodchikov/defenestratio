package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.jpa.ChallengeRepository;
import com.bsuir.defenestratio.service.ChallengeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private ChallengeRepository challengeRepository;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public void saveChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @Override
    public List<Challenge> findAllChallenges() {
        return challengeRepository.findAll();
    }

    @Override
    public Challenge findChallengeById(Long challengeId) {
        return challengeRepository.findChallengeById(challengeId);
    }

    @Override
    public void deleteChallengeById(Long challengeId) {
        challengeRepository.deleteChallengeById(challengeId);
    }

    @Override
    public List<Challenge> findAll(int offset, int limit) {
        return null;
    }
}
