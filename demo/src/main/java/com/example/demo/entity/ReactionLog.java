package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReactionLog extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "reaction")
    private Reaction reaction;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Users receiver;

}
