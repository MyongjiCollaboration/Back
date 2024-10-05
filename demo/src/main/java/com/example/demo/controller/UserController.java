package com.example.demo.controller;

import com.example.demo.authentication.user.AuthenticatedUser;
import com.example.demo.dto.request.FamNameDto;
import com.example.demo.dto.response.MyPageResponseData;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/myPage")
    public ResponseEntity<ResponseDto<MyPageResponseData>> myPage(@AuthenticatedUser Users user) {
        MyPageResponseData myPageResponseData = this.userService.myPage(user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", myPageResponseData), HttpStatus.OK);
    }

    @PatchMapping("/famName")
    public ResponseEntity<ResponseDto<Void>> changeFamName(@AuthenticatedUser Users user, @Valid @RequestBody FamNameDto famName) {
        this.userService.changeFamName(user, famName);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK"), HttpStatus.OK);
    }
}
