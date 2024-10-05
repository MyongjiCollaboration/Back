package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "diary")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;
}
