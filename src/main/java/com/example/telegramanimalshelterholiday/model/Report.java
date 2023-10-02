package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate reportDate;
    private byte[] photo;
    private String diet;
    private String stateOfHealth;
    private String behavior;

    public Report(LocalDate reportDate, byte[] photo, String diet, String stateOfHealth, String behavior) {
        this.reportDate = reportDate;
        this.photo = photo;
        this.diet = diet;
        this.stateOfHealth = stateOfHealth;
        this.behavior = behavior;
    }

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getStateOfHealth() {
        return stateOfHealth;
    }

    public void setStateOfHealth(String stateOfHealth) {
        this.stateOfHealth = stateOfHealth;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(reportDate, report.reportDate) && Arrays.equals(photo, report.photo) && Objects.equals(diet, report.diet) && Objects.equals(stateOfHealth, report.stateOfHealth) && Objects.equals(behavior, report.behavior);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, reportDate, diet, stateOfHealth, behavior);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", photo=" + Arrays.toString(photo) +
                ", diet='" + diet + '\'' +
                ", stateOfHealth='" + stateOfHealth + '\'' +
                ", behavior='" + behavior + '\'' +
                '}';
    }

    /*
    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;*/

    /*на стороне Animal
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<Report> reports;*/


}