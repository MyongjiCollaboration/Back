package com.example.demo.authentication.user;

import com.example.demo.authentication.AuthenticationInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class AuthenticationConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthenticatedUserArgumentResolver authenticatedUserArgumentResolver;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/v3/api-docs/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/health");
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authenticatedUserArgumentResolver);
    }
}
