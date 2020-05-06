package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.UserChallenge;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserChallengeService {

    UserChallenge createUserChallenge(Long userId, Long challengeId);

    List<UserChallenge> findAllUserChallenges(Long userId);

    UserChallenge findUserChallengeById(Long userId, Long challengeId);

    void deleteUserChallenge(Long userId, Long challengeId);

    void deleteAllUserChallenges(Long userId);

    UserChallenge updateUserChallenge(Long userId, Long challengeId, UserChallenge userChallenge);

    UserChallenge uploadImageToChallengeResult(MultipartFile file, Long userId, Long challengeId);
}
