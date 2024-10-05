package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "인증", description = "Auth API")
public class AuthController {

    @GetMapping("/test")
    @Operation(summary = "테스트")
    public String test() {
        return "test";
    }
}
