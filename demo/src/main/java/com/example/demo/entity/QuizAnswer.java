package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizAnswer extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

}
