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
    /**
     * Модель контракт.
     * Модель контракт является связующим звеном между моделями: животным и усыновителем.
     */
    @Id
    @SequenceGenerator(name="contractSequence",sequenceName = "contract_sequence",allocationSize = 1,initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contractSequence")
    @Column(name="id")
    private Long id;
    @Column(name="contract_data")
    private LocalDate contractDate;


    @OneToMany(mappedBy = "contract")
    @JsonIgnore
    private List<Report> reportList;

}
