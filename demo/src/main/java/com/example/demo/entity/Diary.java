package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary extends BaseEntity {

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private Users user;

    @OneToMany(mappedBy = "diary")
    private List<Comment> comments;

    @OneToMany(mappedBy = "diary")
    private List<DiaryPhoto> photos;
}
