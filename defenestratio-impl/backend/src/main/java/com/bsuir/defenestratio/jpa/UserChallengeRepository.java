package com.bsuir.defenestratio.jpa;

import com.bsuir.defenestratio.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
}
