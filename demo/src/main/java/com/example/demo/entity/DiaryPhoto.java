package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DiaryPhoto extends BaseEntity {

    private String url;

    @ManyToOne
    @JoinColumn(name = "diary")
    private Diary diary;
}
