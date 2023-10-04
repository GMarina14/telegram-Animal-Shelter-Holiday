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
    @SequenceGenerator(name="volunteerSequence",sequenceName = "volunteer_sequence",allocationSize = 1,initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "volunteerSequence")
    private Long id;

    @Column(name="volunteer_name")
    private String name;


    @OneToMany(mappedBy = "volunteer")
    @JsonIgnore
    private List<Adopter> adopterList;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}
