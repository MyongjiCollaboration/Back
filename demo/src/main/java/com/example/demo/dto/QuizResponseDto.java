package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class QuizResponseDto {
    private UUID id;
    private String content;
}
