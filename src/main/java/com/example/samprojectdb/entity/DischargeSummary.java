package com.example.samprojectdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DischargeSummary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dsId;
    @Column(nullable = false)
    private Date dischargeAt;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private String outcome;
    @Column(nullable = false)
    private String treatmentProtocol;
    @ManyToOne
    @JsonIgnore
    private AWW aww;
    @OneToMany(mappedBy = "dischargeSummary")
    private List<FollowUp> followUps = new ArrayList<>();



    @OneToOne
    @JoinColumn(name = "admissionId")
    private Admission admission;

    public DischargeSummary()
    {

    }
    public DischargeSummary(int dsId, Date dischargeAt, double weight, String outcome, String treatmentProtocol) {
        super();
        this.dsId = dsId;
        this.dischargeAt = dischargeAt;
        this.weight = weight;
        this.outcome = outcome;
        this.treatmentProtocol = treatmentProtocol;
    }

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public Date getDischargeAt() {
        return dischargeAt;
    }

    public void setDischargeAt(Date dischargeAt) {
        this.dischargeAt = dischargeAt;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }


    public String getTreatmentProtocol() {
        return treatmentProtocol;
    }

    public void setTreatmentProtocol(String treatmentProtocol) {
        this.treatmentProtocol = treatmentProtocol;
    }

    public AWW getAww() {
        return aww;
    }

    public void setAww(AWW aww) {
        this.aww = aww;
    }

    public List<FollowUp> getFollowUps() {
        return followUps;
    }

    public void setFollowUps(List<FollowUp> followUps) {
        this.followUps = followUps;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }
}
