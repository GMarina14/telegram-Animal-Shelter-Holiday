package com.example.telegramanimalshelterholiday.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Contract {
    @Id
    @SequenceGenerator(name="contractSequence",sequenceName = "contract_sequence",allocationSize = 1,initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contractSequence")
    private Long id;
    private LocalDate contractDate;


    @OneToMany(mappedBy = "contract")
    @JsonIgnore
    private List<Report> reportList;

}
