package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DiaryPhoto extends BaseEntity {

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary")
    private Diary diary;
}
