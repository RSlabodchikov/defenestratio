package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.ChallengeRepository;
import com.bsuir.defenestratio.service.ChallengeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private ChallengeRepository challengeRepository;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public List<Challenge> findAllChallenges() {
        return challengeRepository.findAll();
    }

    @Override
    public void updateChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @Override
    public Challenge findChallengeById(Long challengeId) {
        return challengeRepository.findById(challengeId).orElseThrow(
                () -> new NotFoundException(String.format("Cannot found challenge with id %s", challengeId)));
    }

    @Override
    public void deleteChallenge(Long challengeId) {
         challengeRepository.deleteById(challengeId);
    }

    @Override
    public List<Challenge> findAllByTheme(String theme){
        return challengeRepository.findAllByTheme(theme);
    }
}
