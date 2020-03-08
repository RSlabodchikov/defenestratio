package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.Challenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {

    Optional<Challenge> get(Long id);

    void save(Challenge challenge);

    List<Challenge> findAll(int offset, int limit);

}
