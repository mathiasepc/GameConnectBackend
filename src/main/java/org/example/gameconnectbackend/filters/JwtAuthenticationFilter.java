package org.example.gameconnectbackend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.services.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor

// This filter will be called every time a request comes in
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // So we can validate the JWT Token
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        // If header is not present or is not Bearer, do not allow access
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Spring Security will handle the response
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader.replace("Bearer ", "");
        // If token is invalid, do not allow access
        if(!jwtService.validateJwtToken(token)) {
            // Spring Security will handle the response
            filterChain.doFilter(request, response);
            return;
        }

        // UsernamePasswordAuthenticationToken have 2 constructors.
        // We set the second constructor of UsernamePasswordAuthenticationToken
        // - the second constructor to fetch an authenticated users.
        // Object principal: to the email of the user or what ever object we use to identify the user
        var authentication = new UsernamePasswordAuthenticationToken(
                jwtService.getEmailFromToken(token),
                null,
                null
        );

        // Here we attach meta data from the request like ip-address
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // Stores information about the currently authenticated user.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // passes the request along the filter chain
        filterChain.doFilter(request, response);
    }
}
