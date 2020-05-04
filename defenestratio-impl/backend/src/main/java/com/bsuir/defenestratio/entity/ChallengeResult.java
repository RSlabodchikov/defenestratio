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


    public ChallengeResult() {
    }

    public ChallengeResult(Boolean approved, String comment) {
        this.approved = approved;
        this.comment = comment;
    }

    public ChallengeResult(byte[] resultPicture, Boolean approved, String comment) {
        this.resultPicture = resultPicture;
        this.approved = approved;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getResultPicture() {
        return resultPicture;
    }

    public void setResultPicture(byte[] resultPicture) {
        this.resultPicture = resultPicture;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
