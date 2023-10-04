package com.example.telegramanimalshelterholiday.model;

import com.pengrad.telegrambot.model.Contact;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Report {

    /**
     * Модель создания ежедневных отчетов при усыновлении животного.
     * Связь между животными, усыновителями и волонтером осуществляется через контракт.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate reportDate;
    private byte[] photo;
    private String diet;
    private String stateOfHealth;
    private String behavior;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contract contractId; // this field is needed


/*    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animalId;

    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopterId;*/

    public Report(Contact ContractId, LocalDate reportDate, byte[] photo, String diet, String stateOfHealth, String behavior) {

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
        return "Report " + '\n' +
                "id " + id + '\n' +
                "reportDate " + reportDate + '\n' +
                // "photo " + Arrays.toString(photo) +
                "diet: " + diet + '\n' +
                "state of health: " + stateOfHealth + '\n' +
                "behavior: " + behavior;

    }
}
