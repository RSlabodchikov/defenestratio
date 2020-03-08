package com.bsuir.defenestratio.controller;

import com.bsuir.defenestratio.entity.Profile;
import com.bsuir.defenestratio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfilePageController {

    private ProfileService profileService;

    @Autowired
    public ProfilePageController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public Profile getProfileInfo(long id) {
        return profileService.getProfile(id);
    }
}
