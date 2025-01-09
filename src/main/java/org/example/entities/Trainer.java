package org.example.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Trainer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String specialization;


    @OneToMany(mappedBy = "trainer")
    private List<TrainingSession> trainingSessions;
}
