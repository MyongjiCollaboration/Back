package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends BaseEntity {

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;
}
