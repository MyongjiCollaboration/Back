package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Users extends BaseEntity {

    private String name;
    private String email;
    private String role;
    private String password;
    private String birthDate;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<QuizAnswer> quizAnswers;

    @OneToMany(mappedBy = "sender")
    private List<ReactionLog> sentReactions;

    @OneToMany(mappedBy = "receiver")
    private List<ReactionLog> receivedReactions;

    @OneToMany(mappedBy = "user")
    private List<ReactionCount> reactionCounts;

}
