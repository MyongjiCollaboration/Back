package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageResponseData {
    String userName;
    String familyName;
    String familyCode;

    public static MyPageResponseData of(String userName, String familyName, String familyCode) {
        return new MyPageResponseData(userName, familyName, familyCode);
    }
}
