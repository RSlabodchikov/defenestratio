package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.*;
import com.bsuir.defenestratio.exceptions.IncorrectFileFormatException;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.UserChallengeRepository;
import com.bsuir.defenestratio.service.ChallengeService;
import com.bsuir.defenestratio.service.UserChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
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

    @Override
    public UserChallenge uploadImageToChallengeResult(MultipartFile file, Long userId, Long challengeId) {
        UserChallenge userChallenge = findUserChallengeById(userId, challengeId);
        try {
            Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            userChallenge.getChallengeResult().setImage(image);

            return userChallengeRepository.save(userChallenge);
        } catch (IOException ex) {
            log.warn("Cannot get bytes from file {}", file.getOriginalFilename());
            throw new IncorrectFileFormatException(
                    String.format("Cannot get bytes from file %s", file.getOriginalFilename()));

        }
    }
}
