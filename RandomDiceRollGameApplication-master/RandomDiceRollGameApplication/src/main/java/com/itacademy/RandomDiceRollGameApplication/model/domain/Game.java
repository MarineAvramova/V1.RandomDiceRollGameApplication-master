package com.itacademy.RandomDiceRollGameApplication.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @Column
    private int dice1;
    @Column
    private int dice2;
    @Column
    boolean gameWon = false;
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;

    public int throwDice() {
        Random rand = new Random();
        this.dice1 = rand.nextInt(6) + 1;
        this.dice2 = rand.nextInt(6) + 1;
        int sum = this.dice1 + this.dice2;

        if (sum == 7) {
            this.gameWon = true;
        } else {
            this.gameWon = false;
        }

        return sum;
    }
}
