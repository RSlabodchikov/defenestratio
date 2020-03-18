package com.bsuir.defenestratio.jpa;

import com.bsuir.defenestratio.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value = "SELECT * from profiles p WHERE p.id IN (SELECT u.id from users u where u.id = :inputId)", nativeQuery = true)
    Profile findByUserId(@Param("inputId") Long userId);
}
