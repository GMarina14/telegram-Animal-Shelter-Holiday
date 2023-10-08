package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
public class Adopter {
    @Id
    @SequenceGenerator(name = "adopterSequence", sequenceName = "adopter_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adopterSequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "prob_extend")
    private Integer probExtend;

    public Adopter(String firstName, String lastName, Integer probExtend) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.probExtend = probExtend;
    }

    public Adopter() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getProbExtend() {
        return probExtend;
    }

    public void setProbExtend(Integer probExtend) {
        this.probExtend = probExtend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adopter adopter = (Adopter) o;
        return Objects.equals(getId(), adopter.getId()) && Objects.equals(getFirstName(), adopter.getFirstName()) && Objects.equals(getLastName(), adopter.getLastName()) && Objects.equals(getProbExtend(), adopter.getProbExtend());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getProbExtend());
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", probExtend=" + probExtend +
                '}';
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contract",
            joinColumns = @JoinColumn(name = "adopter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id", referencedColumnName = "id"))
    private List<Animal> animalList;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

}

