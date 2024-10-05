package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event extends BaseEntity {
    private String name;
    private String content;
    private String place;
    private String date;
    private String time;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;
}
