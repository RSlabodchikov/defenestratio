package com.bsuir.defenestratio.jpa;

import com.bsuir.defenestratio.entity.ChallengeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeResultRepository extends JpaRepository<ChallengeResult, Long> {
}
