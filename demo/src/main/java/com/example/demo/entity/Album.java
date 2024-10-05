package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Album extends BaseEntity{

    private String name;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;

    @OneToMany(mappedBy = "album")
    private List<AlbumPhoto> photos;

}
