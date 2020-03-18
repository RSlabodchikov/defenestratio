package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.ProfileRepository;
import com.bsuir.defenestratio.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public Profile findProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId);
        if (profile != null) {
            return profile;
        }
        throw new NotFoundException(
                String.format("Cannot found profile for user with id %s", userId));
    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.save(profile);
    }
}
