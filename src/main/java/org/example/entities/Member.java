package org.example.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Members")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String membershipType;

    @ManyToMany(mappedBy = "members")
    private List<TrainingSession> trainingSessions;

    @OneToOne(mappedBy = "member")
    private Subscription subscription;

    @OneToMany(mappedBy = "member")
    private List<Progress> progressList;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservationList;
}
