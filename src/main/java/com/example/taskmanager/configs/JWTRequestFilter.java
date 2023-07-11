package com.example.taskmanager.configs;

import com.example.taskmanager.utils.JWTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JWTokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwtToken = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwtToken = authHeader.substring(7);
//            try {
//                username = tokenUtils.getUsername(jwtToken);
//            } catch (ExpiredJwtException e) {
//                log.debug("Token expired");
//            } catch (SignatureException e) {
//                log.debug("Signature error");
//            }
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UsernamePasswordAuthenticationToken authenticationToken
//                        = new UsernamePasswordAuthenticationToken(
//                        username,
//                        null,
//                        tokenUtils.getRoles(jwtToken).stream().map(SimpleGrantedAuthority::new).toList()
//                );
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
    }
}
