package com.example.server_manager.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import io.jsonwebtoken.JwtException;

import java.io.IOException;

public class JwtAuthFilter implements Filter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;  // JwtService parametresi alıyoruz
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = getJwtFromRequest(httpRequest);  // Header'dan JWT token'ı alıyoruz

        if (token != null && jwtService.validateToken(token)) {
            String username = jwtService.extractUsername(token);  // Token'dan kullanıcı adını alıyoruz

            // Token geçerli ise kullanıcıyı Spring Security bağlamına ekliyoruz
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);

            // Spring Security bağlamına kimlik doğrulama objesini yerleştiriyoruz
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);  // Bir sonraki filtreyi çağırıyoruz
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // "Bearer " kısmını atıyoruz
        }
        return null;
    }
}
