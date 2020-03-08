package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.service.ChallengeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public Optional<Challenge> get(Long id) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void save(Challenge challenge) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<Challenge> findAll(int offset, int limit) {
        throw new UnsupportedOperationException("TODO");
    }
}
