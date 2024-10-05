package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz extends BaseEntity{

    private String content;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;

    @OneToMany(mappedBy = "quiz")
    private List<QuizAnswer> quizAnswers;

}
