package com.bsuir.defenestratio.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "total_points")
    private Integer totalPoints;

    @Column(name = "rating")
    private Integer rating;
}
