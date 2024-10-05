package com.example.demo.controller;

import com.example.demo.dto.request.user.LoginDto;
import com.example.demo.dto.request.user.SignupDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    // 회원 가입
    @PostMapping("/auth/signIn")
    public ResponseEntity<ResponseDto<Void>> createUser(@Valid @RequestBody SignupDto createUserDto) {
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
