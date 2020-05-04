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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "comment")
    private String comment;

    public ChallengeResult(Boolean approved, String comment) {
        this.approved = approved;
        this.comment = comment;
        this.image = null;
    }
}
