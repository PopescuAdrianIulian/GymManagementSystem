package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//id: ID unic pentru fiecare rezervare.
//equipmentId: ID-ul echipamentului rezervat.
//memberId: ID-ul membrului care face rezervarea.
//reservationDate: Data și ora rezervării.
//duration: Durata rezervării (ex: 1 oră).
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int equipmentId;
    private int memberId;
    private LocalDateTime reservationDate;
    private int duration;
    private LocalDateTime reservationEnd;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", equipmentId=" + equipmentId +
                ", memberId=" + memberId +
                ", reservationDate=" + reservationDate +
                ", duration=" + duration +
                ", reservationEnd=" + reservationEnd +
                '}';
    }
}
