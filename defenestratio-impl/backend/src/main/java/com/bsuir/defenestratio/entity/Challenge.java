package com.bsuir.defenestratio.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "challenge")
@Data
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "level")
    private Integer level;

    @Column(name = "points")
    private Integer points;

}
