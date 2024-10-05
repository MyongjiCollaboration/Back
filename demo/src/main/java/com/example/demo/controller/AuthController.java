package com.example.demo.controller;

import com.example.demo.dto.request.user.LoginDto;
import com.example.demo.dto.request.user.SignupDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "인증", description = "Auth API")
public class AuthController {
    private final AuthService authService;

    // 회원 가입
    @PostMapping("/auth/signIn")
    public ResponseEntity<ResponseDto<Void>> createUser(@Valid @RequestBody SignupDto createUserDto) {
        log.info("2-1");
        this.authService.signup(createUserDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "create User"), HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDto<Void>> login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {
        this.authService.login(loginDto, response);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "login successfully"), HttpStatus.OK);
    }
}
