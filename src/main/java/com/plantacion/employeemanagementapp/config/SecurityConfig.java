package com.plantacion.employeemanagementapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] WHITELIST = {
            "/", "/home", "/register"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> {
                    request.requestMatchers( WHITELIST).permitAll();
                    request.anyRequest().authenticated();
                })
                .formLogin(form ->
                        form.loginPage("/login")
                                .defaultSuccessUrl("/")
                                .failureUrl("/error")
                );


        return http.build();
    }

}
