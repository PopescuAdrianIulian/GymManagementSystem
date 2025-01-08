package org.example.entities;

import lombok.*;

import javax.persistence.*;

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


}
