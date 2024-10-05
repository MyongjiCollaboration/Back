package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class QuizAnswerRequestDto {

    private UUID quizId;


    private UUID userId;

    private String content;

}
