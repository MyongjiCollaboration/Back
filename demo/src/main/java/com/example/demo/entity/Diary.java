package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Diary extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

    @OneToMany(mappedBy = "diary")
    private List<Comment> comments;

    @OneToMany(mappedBy = "diary")
    private List<DiaryPhoto> photos;
}
