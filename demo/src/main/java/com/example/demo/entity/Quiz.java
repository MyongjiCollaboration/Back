package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Quiz extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;

    @OneToMany(mappedBy = "quiz")
    private List<QuizAnswer> quizAnswers;

}
