package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int memberId;
    private String date;
    private int weight;
    private int bodyFatPercentage;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
