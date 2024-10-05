package com.example.demo.exception;

import com.example.demo.exception.error.ErrorCode;

public class DtoValidationException extends CustomException {
    public DtoValidationException(ErrorCode errorCode, String detail) {super(errorCode, detail);}
}
