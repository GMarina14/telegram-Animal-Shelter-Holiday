package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@EqualsAndHashCode
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

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;


    public Adopter(String firstName, String lastName, Integer probExtend, Long chatId, String userName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.probExtend = probExtend;
        this.chatId = chatId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contract",
            joinColumns = @JoinColumn(name = "adopter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id", referencedColumnName = "id"))
    private List<Animal> animalList;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

}

