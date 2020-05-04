package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Challenge;
import com.bsuir.defenestratio.entity.ChallengeResult;
import com.bsuir.defenestratio.entity.ChallengeStatus;
import com.bsuir.defenestratio.entity.UserChallenge;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.UserChallengeRepository;
import com.bsuir.defenestratio.service.ChallengeService;
import com.bsuir.defenestratio.service.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

    private UserChallengeRepository userChallengeRepository;

    private ChallengeService challengeService;

    @Autowired
    public UserChallengeServiceImpl(UserChallengeRepository userChallengeRepository, ChallengeService challengeService) {
        this.userChallengeRepository = userChallengeRepository;
        this.challengeService = challengeService;
    }

    @Override
    public UserChallenge createUserChallenge(Long userId, Long challengeId) {
        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setUserId(userId);
        userChallenge.setChallenge(new Challenge(challengeId));
        userChallenge.setChallengeResult(new ChallengeResult(false, "Challenge accepted"));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        userChallenge.setDueDate(new Date(calendar.getTime().getTime()));
        userChallenge.setStatus(ChallengeStatus.IN_PROGRESS);

        return userChallengeRepository.save(userChallenge);
    }

    @Override
    public List<UserChallenge> findAllUserChallenges(Long userId) {
        return userChallengeRepository.findAllByUserId(userId);
    }

    @Override
    public UserChallenge findUserChallengeById(Long userId, Long challengeId) {
        return userChallengeRepository.findByChallengeIdAndUserId(challengeId, userId)
                .orElseThrow(() -> new NotFoundException("Cannot found user challenge"));
    }

    @Override
    public void deleteUserChallenge(Long userId, Long challengeId) {
        userChallengeRepository.deleteByUserIdAndChallengeId(userId, challengeId);
    }

    @Override
    public void deleteAllUserChallenges(Long userId) {
        userChallengeRepository.deleteByUserId(userId);
    }

    @Override
    public UserChallenge updateUserChallenge(Long userId, Long challengeId, UserChallenge userChallenge) {
        return userChallengeRepository.save(userChallenge);
    }
}
