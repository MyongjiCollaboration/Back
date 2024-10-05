package com.example.demo.exception;

import com.example.demo.exception.error.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
