package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AlbumPhoto extends BaseEntity {

    private String url;

    @ManyToOne
    @JoinColumn(name = "album")
    private Album album;
}
