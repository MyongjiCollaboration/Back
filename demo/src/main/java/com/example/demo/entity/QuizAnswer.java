package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class QuizAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

}
