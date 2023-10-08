package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Adopter extends Client {
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

    public Adopter(Long chatId, String userName, String phoneNumber, Long id, String firstName, String lastName, Integer probExtend) {
        super(chatId, userName, phoneNumber);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.probExtend = probExtend;
    }

    public Adopter(Long id, String firstName, String lastName, Integer probExtend) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.probExtend = probExtend;
    }

    public Adopter(Long chatId, String userName, String phoneNumber) {
        super(chatId, userName, phoneNumber);
    }

    public Adopter() {

    }


    @Override
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
        if (!super.equals(o)) return false;
        Adopter adopter = (Adopter) o;
        return Objects.equals(id, adopter.id) && Objects.equals(firstName, adopter.firstName) && Objects.equals(lastName, adopter.lastName) && Objects.equals(probExtend, adopter.probExtend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, probExtend);
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

