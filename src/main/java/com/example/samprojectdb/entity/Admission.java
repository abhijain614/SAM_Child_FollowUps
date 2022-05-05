package com.example.samprojectdb.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admissionId;
    @Column(nullable = false)
    private Date admittedAt;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Column(nullable = false)
    private double weight;

    @ManyToOne
    @JsonIgnore
    private NRC nrc;

    @ManyToOne
    private Child child;

    @OneToOne(mappedBy = "admission")
    @JsonIgnore
    private DischargeSummary dischargeSummary;

    public int getAdmissionId() {
        return admissionId;
    }

    public Admission()
    {

    }

    public Admission(int admissionId, Date admittedAt, DischargeSummary dischargeSummary) {
        super();
        this.admissionId = admissionId;
        this.admittedAt = admittedAt;
        this.dischargeSummary = dischargeSummary;
    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public Date getAdmittedAt() {
        return admittedAt;
    }

    public void setAdmittedAt(Date admittedAt) {
        this.admittedAt = admittedAt;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
