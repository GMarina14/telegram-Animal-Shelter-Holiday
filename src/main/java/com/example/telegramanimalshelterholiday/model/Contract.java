package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contractNumber;
    private Date contractDate;

    @ManyToOne
    @JoinColumn(name="volunteer_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name="adopter_id")
    private Adopter adopter;

}
