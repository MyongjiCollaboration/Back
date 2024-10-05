package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
public class Reaction extends BaseEntity {


    private int missu;
    private int loveu;
    private int worry;
    private int cheerup;

    @OneToMany(mappedBy = "reaction")
    private List<ReactionLog> reactionLogs;

}
