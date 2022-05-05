package com.example.samprojectdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class FollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followUpId;
    @Column(nullable = true)
    private Double height;
    @Column(nullable = true)
    private Double weight;
    @Column(nullable = true)
    private Double muac;
    @Column(nullable = true)
    private String growthStatus;
    @Column(nullable = true)
    private String symptoms;
    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private LocalDate followupDate;
    @Column(nullable = false)
    private Boolean isAttempted;
    @Column
    private LocalDate attemptedDate;
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private DischargeSummary dischargeSummary;

    public FollowUp() {

    }

    public FollowUp(Double height, Double weight, Double muac, String growthStatus, String symptoms, LocalDate createdAt, LocalDate followupDate, Boolean isAttempted, LocalDate attemptedDate) {
        super();
        //this.followUpId = followUpId;
        this.height = height;
        this.weight = weight;
        this.muac = muac;
        this.growthStatus = growthStatus;
        this.symptoms = symptoms;
        this.createdAt = createdAt;
        this.followupDate = followupDate;
        this.isAttempted = isAttempted;
        this.attemptedDate = attemptedDate;
    }

    public FollowUp(LocalDate createdAt, LocalDate followupDate, Boolean isAttempted, DischargeSummary dischargeSummary) {
        super();
        this.createdAt = createdAt;
        this.followupDate = followupDate;
        this.isAttempted = isAttempted;
        //this.attemptedDate = attemptedDate;
        this.dischargeSummary = dischargeSummary;
    }

    @Override
    public String toString() {
        return "FollowUp{" +
                "followUpId=" + followUpId +
                ", height=" + height +
                ", weight=" + weight +
                ", muac=" + muac +
                ", growthStatus='" + growthStatus + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", createdAt=" + createdAt +
                ", followupDate=" + followupDate +
                ", isAttempted=" + isAttempted +
                ", attemptedDate=" + attemptedDate +
                '}';
    }

    public Integer getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(Integer followUpId) {
        this.followUpId = followUpId;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getMuac() {
        return muac;
    }

    public void setMuac(Double muac) {
        this.muac = muac;
    }

    public String getGrowthStatus() {
        return growthStatus;
    }

    public void setGrowthStatus(String growthStatus) {
        this.growthStatus = growthStatus;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(LocalDate followupDate) {
        this.followupDate = followupDate;
    }

    public Boolean getAttempted() {
        return isAttempted;
    }

    public void setAttempted(Boolean attempted) {
        isAttempted = attempted;
    }

    public LocalDate getAttemptedDate() {
        return attemptedDate;
    }

    public void setAttemptedDate(LocalDate attemptedDate) {
        this.attemptedDate = attemptedDate;
    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }
}
