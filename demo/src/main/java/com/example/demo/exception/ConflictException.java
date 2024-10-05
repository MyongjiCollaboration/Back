package com.example.demo.exception;

import com.example.demo.exception.error.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(ErrorCode errorCode) {super(errorCode);}
}
