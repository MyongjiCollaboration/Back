package com.example.demo.authentication.user;

import com.example.demo.entity.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@Component
@RequestScope
public class AuthenticationContext {
    private Users principal;
}
