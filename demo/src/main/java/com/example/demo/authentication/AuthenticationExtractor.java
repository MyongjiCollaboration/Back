package com.example.demo.authentication;

import com.example.demo.exception.UnauthorizedException;
import com.example.demo.exception.error.ErrorCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationExtractor {
    public static final String TOKEN_COOKIE_NAME = "AccessToken";

    public static String extract(final HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        log.info("쿠키 뽑음");
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    return JwtEncoder.decode(String.valueOf(cookie.getValue()));
                }
            }
        }
        throw new UnauthorizedException(ErrorCode.COOKIE_NOT_FOUND, "extract할 cookie를 찾을 수 없습니다.");
    }
}

