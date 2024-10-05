package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Diary extends BaseEntity {

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
