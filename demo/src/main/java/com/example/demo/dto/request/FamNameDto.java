package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FamNameDto {
    @NotNull
    String famName;
}
