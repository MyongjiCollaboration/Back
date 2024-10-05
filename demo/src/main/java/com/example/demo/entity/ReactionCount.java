package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReactionCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "reaction")
    private Reaction reaction;

}
