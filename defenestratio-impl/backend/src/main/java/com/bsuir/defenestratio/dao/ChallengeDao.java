package com.bsuir.defenestratio.dao;

import com.bsuir.defenestratio.entity.Challenge;

import java.util.List;

public interface ChallengeDao {

    List<Challenge> findAllChallengesAvailableForUser(Long userId);

}
