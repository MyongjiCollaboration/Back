package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class QuizAnswer extends BaseEntity {

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

}
