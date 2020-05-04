package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile findProfile(Long userId);

    void updateProfile(Profile profile);

    List<Profile> getProfilesSortedByRating();
}
