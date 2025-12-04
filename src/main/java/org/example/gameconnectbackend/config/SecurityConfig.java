package org.example.gameconnectbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(c ->
                        // Stateless session we're using token based authentication
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // Here we configure our HttpSecurity
                .authorizeHttpRequests(c -> {
                    c.anyRequest().permitAll();
                    //c.requestMatchers("/create-post").hasRole("USER");
                });

        return http.build();
    }
}
