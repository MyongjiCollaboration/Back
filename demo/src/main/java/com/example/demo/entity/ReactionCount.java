package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
public class ReactionCount extends BaseEntity{

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction")
    private Reaction reaction;

}
