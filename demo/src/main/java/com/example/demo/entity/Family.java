package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Family extends BaseEntity{

    String name;

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    private List<Users> users;

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    private List<Event> events;

    @OneToMany(mappedBy = "family")
    private List<Quiz> quizzes;

}
