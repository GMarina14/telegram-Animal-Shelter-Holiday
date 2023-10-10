package com.example.telegramanimalshelterholiday.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Report {

    /**
     * Модель создания ежедневных отчетов при усыновлении животного.
     * Связь между животными, усыновителями и волонтером осуществляется через контракт.
     */
    @Id
    @SequenceGenerator(name = "reportSequence", sequenceName = "report_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "report_data")
    private LocalDate reportDate;

    /**
     * Аннотация @Lob подсказывает Hibernate, что в поле хранится Large Object.
     */

   @Column(name = "photo")
    private byte[] photo;
    @Column(name = "diet")
    private String diet;
    @Column(name = "health")
    private String stateOfHealth;
    @Column(name = "behavior")
    private String behavior;


    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

}
