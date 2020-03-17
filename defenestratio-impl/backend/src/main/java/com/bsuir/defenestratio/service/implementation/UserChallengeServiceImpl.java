package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.UserChallenge;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.UserChallengeRepository;
import com.bsuir.defenestratio.service.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

    private UserChallengeRepository userChallengeRepository;

    @Autowired
    public UserChallengeServiceImpl(UserChallengeRepository userChallengeRepository) {
        this.userChallengeRepository = userChallengeRepository;
    }

    @Override
    public UserChallenge createUserChallenge(UserChallenge userChallenge) {
        return userChallengeRepository.save(userChallenge);
    }

    @Override
    public List<UserChallenge> findAllUserChallenges(Long userId) {
        return userChallengeRepository.findAllByUserId(userId);
    }

    @Override
    public UserChallenge findUserChallengeById(Long userId, Long challengeId) {
        return userChallengeRepository.findByChallengeIdAndUserId(challengeId, userId)
                .orElseThrow(() -> new NotFoundException("Cannot found user challenge"));
    }

    @Override
    public void deleteUserChallenge(Long userId, Long challengeId) {
        userChallengeRepository.deleteByUserIdAndChallengeId(userId, challengeId);
    }

    @Override
    public void deleteAllUserChallenges(Long userId) {
        userChallengeRepository.deleteByUserId(userId);
    }
}
