package com.example.demo.authentication;

import com.example.demo.authentication.user.AuthenticationContext;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationContext authenticationContext;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = AuthenticationExtractor.extract(request);
        UUID userId = UUID.fromString(jwtTokenProvider.getPayload(accessToken));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        authenticationContext.setPrincipal(user);
        log.info("set user");
        return true;
    }
}
