package com.example.telegramanimalshelterholiday.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Volunteer {
    /**
     * Модель волонтер.
     * Данная модель связана с усыновителем и приютом.
     */
    @Id
    @SequenceGenerator(name = "volunteerSequence", sequenceName = "volunteer_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volunteerSequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "volunteer_name")
    private String name;
    @Column(name = "chat_id")
    private Long chatId;

    @OneToMany(mappedBy = "volunteer")
    @JsonIgnore
    private List<Adopter> adopterList;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}
