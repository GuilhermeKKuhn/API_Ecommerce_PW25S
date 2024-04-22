package com.example.Ecommerce.security;

import com.example.Ecommerce.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurity {

    private final AuthService authService;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public WebSecurity(AuthService authService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.authService = authService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.build();

        //desabilita a proteção de frame
        http.headers( headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        //simplifica a autenticação em aplicações sem estado
        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(
                        authenticationEntryPoint));
        //desabilita a configurção do CORD
        http.cors(AbstractHttpConfigurer::disable);

        //Define regras de autorização para diferentes tipos de requisições HTTP
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .anyRequest().authenticated()
        );

        http.authenticationManager(authenticationManager)
                .addFilter(
                        new JWTAuthenticationFilter(
                                authenticationManager,
                                authService)
                )
                .addFilter(
                        new JWTAuthorizationFilter(
                                authenticationManager,
                                authService)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
