package com.example.demo.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Data
public class QuizAnswerRequestDto {

    private UUID quizId;


    private UUID userId;

    private String content;

}
