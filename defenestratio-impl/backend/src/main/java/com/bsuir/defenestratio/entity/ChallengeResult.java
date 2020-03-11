package com.bsuir.defenestratio.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "challenge_results")
public class ChallengeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "comment")
    private String comment;
}
