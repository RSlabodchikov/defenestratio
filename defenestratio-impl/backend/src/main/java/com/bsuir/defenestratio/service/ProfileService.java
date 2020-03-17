package com.bsuir.defenestratio.service;

import com.bsuir.defenestratio.entity.Profile;

public interface ProfileService {
    Profile findProfile(Long userId);

    void updateProfile(Profile profile);
}
