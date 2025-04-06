package com.reboott.linguatech.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf { it.disable() } // Swagger 테스트 편하게 CSRF 비활성화
                .authorizeHttpRequests {
                    it
                            .requestMatchers(
                                    "/v3/api-docs/**",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html",
                                    "/swagger-resources/**",
                                    "/webjars/**"
                            ).permitAll()
                            .anyRequest().permitAll() // 모든 요청 허용
                }
                .httpBasic {} // 로그인 방식 지정

        return http.build()
    }
}
