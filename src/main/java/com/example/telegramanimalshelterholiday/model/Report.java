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
    private Long id;
    private LocalDate reportDate;

    /**
     * Аннотация @Lob подсказывает Hibernate, что в поле хранится Large Object.
     * А columnDefinition="BLOB" уже говорит о том, как это все сохранить в базе.
     */
    @Lob
//    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;
    private String diet;
    private String stateOfHealth;
    private String behavior;


    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

}
