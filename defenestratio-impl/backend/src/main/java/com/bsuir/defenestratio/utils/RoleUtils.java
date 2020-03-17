package com.bsuir.defenestratio.utils;

import com.bsuir.defenestratio.entity.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public final class RoleUtils {

    private final static Map<UUID, Role> roleNames = new HashMap<>();


    private final UUID adminId;

    private final UUID clientId;

    private final UUID mentorId;

    public RoleUtils(@Value("${defenestratio.role.admin}") UUID adminId,
                     @Value("${defenestratio.role.client}") UUID clientId,
                     @Value("${defenestratio.role.mentor}") UUID mentorId) {
        this.adminId = adminId;
        this.clientId = clientId;
        this.mentorId = mentorId;
    }

    @PostConstruct
    private void initializeRoleNames() {
        roleNames.put(adminId, Role.ADMIN);
        roleNames.put(clientId, Role.CLIENT);
        roleNames.put(mentorId, Role.MENTOR);
    }

    public static Map<UUID, Role> getAllRoleNames() {
        return roleNames;
    }
}
