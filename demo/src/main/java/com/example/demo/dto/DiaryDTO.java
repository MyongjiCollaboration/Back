package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DiaryDTO {
    private UUID id;
    private String title, content;
    private LocalDateTime createdAt;
}
