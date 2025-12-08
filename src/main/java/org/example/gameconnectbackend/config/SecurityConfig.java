package org.example.gameconnectbackend.config;

import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.httpfilters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

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
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/validate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/search/**").permitAll()
                        .anyRequest().permitAll()
                )
                // Here we add the filter we made from JwtAuthenticationFilter
                // This filter will run on every request
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Here we configure our exception handling
                .exceptionHandling(c ->
                {
                    c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    c.accessDeniedHandler((request, response, accessDeniedException) ->
                            response.setStatus(HttpStatus.FORBIDDEN.value()));
                })
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure our HttpClient
    @Bean
    public HttpClient HttpClient() {
        return HttpClient.newBuilder()
                // Sets a timeout for the request.
                .connectTimeout(Duration.ofSeconds(50))
                // which HttpClient to use
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    // This bean is used to authenticate the user
    // It handles the authentication logic (login, logout, etc.)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // This bean is used to get the authentication manager
    // It is used by Spring Security to authenticate the user
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }
}
