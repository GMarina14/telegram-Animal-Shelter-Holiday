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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long shatId;

    @OneToMany(mappedBy = "volunteer")
    @JsonIgnore
    private List<Contract> contractList;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}
