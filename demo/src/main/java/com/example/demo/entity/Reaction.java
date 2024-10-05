package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Reaction extends BaseEntity {


    private int missu;
    private int loveu;
    private int worry;
    private int cheerup;

    @OneToMany(mappedBy = "reaction")
    private List<ReactionLog> reactionLogs;

}
