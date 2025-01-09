package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sessionName;

    private LocalDateTime schedule;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToMany()
    @JoinTable(
            name = "member_training_session",
            joinColumns = @JoinColumn(name = "training_session_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;
}
