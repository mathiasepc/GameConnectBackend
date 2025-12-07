package org.example.gameconnectbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpClient igdbHttpClient() {
        return HttpClient.newBuilder()
                // Sets a timeout for the request.
                .connectTimeout(Duration.ofSeconds(50))
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(c ->
                        // Stateless session we're using token based authentication
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // ------------------------------------------
                // !!! Here we configure our HttpSecurity !!!
                .authorizeHttpRequests(c -> c
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().permitAll()
                )
                // -------------------------------------------
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowCredentials(false);// allows taking authentication with credentials
                    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                    // providing the allowed origin details, can provide multiple origins here, 7070 is the port number of client application here
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));// allowing all HTTP methods GET,POST,PUT etc, can configure on your need
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));// allowing all the request headers, can configure according to your need, which headers to allow
                    corsConfiguration.setMaxAge(Duration.ofMinutes(5L)); // setting the max time till which the allowed origin will not make a pre-flight request again to check if the CORS is allowed on not
                    return corsConfiguration;
                }));


        return http.build();
    }
}
