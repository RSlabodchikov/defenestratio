package com.bsuir.defenestratio.jpa;

import com.bsuir.defenestratio.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findAllByUserId(Long userId);

    UserChallenge findUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);

    void deleteAllByUserId(Long userId);

    void deleteUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);
}
