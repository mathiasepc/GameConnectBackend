package org.example.gameconnectbackend.httpfilters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.gameconnectbackend.interfaces.IJwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor

// This filter will be called every time a request comes in
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // So we can validate the JWT Token
    private final IJwtService jwtService;

    // We validate our JWT Token here
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the property Authorization from the header
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

        var role = jwtService.getRoleFromToken(token);
        var userid = jwtService.getUserIdFromToken(token);
        // UsernamePasswordAuthenticationToken have 2 constructors.
        // We set the second constructor of UsernamePasswordAuthenticationToken
        // - the second constructor to fetch an authenticated users.
        // Object principal: fetch the information of the user you choose.
        var authentication = new UsernamePasswordAuthenticationToken(
                userid,
                null,
                // We must add roles like "ROLE_USER". 'ROLE_'
                // is expected by Spring Security
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
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
