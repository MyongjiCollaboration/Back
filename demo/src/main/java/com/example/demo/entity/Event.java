package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String content;
    private String place;
    private String date;
    private String time;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;
}
