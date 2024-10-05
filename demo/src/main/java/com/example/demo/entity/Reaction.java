package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int missu;
    private int loveu;
    private int worry;
    private int cheerup;

    @OneToMany(mappedBy = "reaction")
    private List<ReactionLog> reactionLogs;

}
