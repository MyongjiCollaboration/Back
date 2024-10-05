package com.example.demo.dto.request.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventRequestDto {
    @NotNull
    private String content;
    @NotNull
    private String place;
    @NotNull
    private String date;
}
