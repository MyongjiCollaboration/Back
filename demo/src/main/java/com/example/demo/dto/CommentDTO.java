package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentDTO {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;
}
