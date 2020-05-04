package com.bsuir.defenestratio.jpa;

import com.bsuir.defenestratio.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findAllByUserId(Long userId);

    Optional<UserChallenge> findByChallengeIdAndUserId(Long challengeId, Long userId);

    @Transactional
    void deleteByUserIdAndChallengeId(Long userId, Long challengeId);

    void deleteByUserId(Long userId);

    void deleteByUserIdAndChallenge_Id(Long userId, Long challengeId);
}
