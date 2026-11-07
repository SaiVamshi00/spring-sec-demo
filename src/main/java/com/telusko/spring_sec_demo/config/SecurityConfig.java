package com.telusko.spring_sec_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableMethodSecurity 
@EnableWebSecurity 
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        http.authorizeHttpRequests(auth ->
             auth.requestMatchers("/public").permitAll()
             .requestMatchers("/admin/**").hasRole("ADMIN")
             .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
             .requestMatchers("/auth/**").permitAll()
             .anyRequest()
             .authenticated())
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults()).csrf(csrf->csrf.ignoringRequestMatchers("/auth/**"));
        return http.build();
    }

}
