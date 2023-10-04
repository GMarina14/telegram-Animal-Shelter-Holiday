package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Client {
    @Id
    @SequenceGenerator(name="clientSequence",sequenceName = "client_sequence",allocationSize = 1,initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "clientSequence")
    private Long id;
    private Long chatId;
    private String userName;
    private String phoneNumber;

    public Client(Long chatId, String userName, String phoneNumber) {
        this.chatId = chatId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(chatId, client.chatId) && Objects.equals(userName, client.userName) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, userName, phoneNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", shelter=" + shelter +
                '}';
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;


}
