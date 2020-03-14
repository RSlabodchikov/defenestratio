package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.UserChallenge;
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
    public void saveUserChallenge(UserChallenge userChallenge) {
        userChallengeRepository.save(userChallenge);
    }

    @Override
    public List<UserChallenge> findAllUserChallengesByUserId(Long userId) {
        return userChallengeRepository.findAllByUserId(userId);
    }

    @Override
    public UserChallenge findUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId) {
        return userChallengeRepository.findUserChallengeByUserIdAndChallengeId(userId, challengeId);
    }

    @Override
    public void deleteUserChallengesByUserId(Long userId) {
        userChallengeRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId) {
        userChallengeRepository.deleteUserChallengeByUserIdAndChallengeId(userId, challengeId);
    }
}
