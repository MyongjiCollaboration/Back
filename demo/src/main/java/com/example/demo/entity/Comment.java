package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "diary")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;
}
