package com.itacademy.RandomDiceRollGameApplication.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "nickname")
    private String name;

    @Column(name = "registration_data")
    private LocalDateTime registerDate;

//    @Column(name = "success_Score")
//    private double successScore;

    //@JsonIgnore
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Game> games;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")//, referencedColumnName = "id"
    private User user;
}
