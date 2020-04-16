package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.jpa.ProfileRepository;
import com.bsuir.defenestratio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile findProfile(Long userId) {
        Optional<Profile> profile = profileRepository.findByUserId(userId);
        return profile.orElseThrow(() -> new NotFoundException(
                String.format("Cannot found profile for user with id %s", userId)));
    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.save(profile);
    }
}
