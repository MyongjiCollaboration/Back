package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quiz extends BaseEntity{

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family")
    private Family family;

    @OneToMany(mappedBy = "quiz")
    private List<QuizAnswer> quizAnswers;

}
