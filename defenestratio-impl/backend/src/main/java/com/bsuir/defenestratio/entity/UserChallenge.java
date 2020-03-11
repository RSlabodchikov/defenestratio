package com.bsuir.defenestratio.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "user_challenges")
public class UserChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "challenge_id")
    private Long challengeId;

    @OneToOne
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private ChallengeResult challengeResult;
}
