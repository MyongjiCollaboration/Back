package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
public class Family extends BaseEntity{

    String name;

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    private List<Users> users;

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    private List<Event> events;

    @OneToMany(mappedBy = "family")
    private List<Quiz> quizzes;

}
