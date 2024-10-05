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

    @OneToMany(mappedBy = "family")
    private List<Users> users;

    @OneToMany(mappedBy = "family")
    private List<Event> events;

    @OneToMany(mappedBy = "family")
    private List<Quiz> quizzes;

}
