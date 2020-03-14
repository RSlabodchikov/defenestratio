package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.UserChallenge;

import java.util.List;

public interface UserChallengeService {

    void saveUserChallenge(UserChallenge userChallenge);

    List<UserChallenge> findAllUserChallengesByUserId(Long userId);

    UserChallenge findUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);

    void deleteUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);

    void deleteUserChallengesByUserId(Long challengeId);
}
