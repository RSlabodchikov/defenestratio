package com.bsuir.defenestratio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "challenge_results")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "result_picture")
    private byte[] resultPicture;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "comment")
    private String comment;
}
