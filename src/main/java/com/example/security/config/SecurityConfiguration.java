package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/user/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/user").hasRole("USER")
                                .requestMatchers(HttpMethod.PATCH, "/user").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/user").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/user").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/user").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .build();
    }
}
