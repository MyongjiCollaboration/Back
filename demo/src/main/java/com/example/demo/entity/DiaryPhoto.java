package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DiaryPhoto extends BaseEntity {

    private String url;

    @ManyToOne
    @JoinColumn(name = "diary")
    private Diary diary;
}
