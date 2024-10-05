package com.example.demo.exception;

import com.example.demo.exception.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {super(errorCode);}
}
