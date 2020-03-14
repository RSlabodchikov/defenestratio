package com.bsuir.defenestratio.jpa;

import com.bsuir.defenestratio.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleById(Integer id);
}
