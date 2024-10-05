package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Album extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family")
    private Family family;

    @OneToMany(mappedBy = "album")
    private List<AlbumPhoto> photos;

}
