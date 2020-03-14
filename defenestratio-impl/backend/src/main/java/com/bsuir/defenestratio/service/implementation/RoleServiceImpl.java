package com.bsuir.defenestratio.service.implementation;

import com.bsuir.defenestratio.entity.Role;
import com.bsuir.defenestratio.jpa.RoleRepository;
import com.bsuir.defenestratio.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleRepository.findRoleById(id);
    }
}
