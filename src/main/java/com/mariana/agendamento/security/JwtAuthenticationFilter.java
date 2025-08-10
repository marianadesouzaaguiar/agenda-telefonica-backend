package com.mariana.agendamento.security;

import com.mariana.agendamento.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, @Qualifier("customUserDetailsService")  UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Ignora endpoints públicos para evitar falha de autenticação antecipada
        String path = request.getRequestURI();
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Pega header Authorization
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        // Verifica se o header está presente e começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;  // segue filtro sem autenticação
        }

        // Extrai token JWT do header
        jwtToken = authHeader.substring(7);

        // Extrai username do token
        username = jwtService.extractUsername(jwtToken);

        // Se username existe e ainda não está autenticado no contexto do Spring Security
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Carrega detalhes do usuário (usuário, permissões, etc)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Valida se token é válido para esse usuário
            if (jwtService.isTokenValid(jwtToken, userDetails)) {

                // Cria objeto de autenticação do Spring
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                // Adiciona detalhes da requisição
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Coloca autenticação no contexto do Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continua filtro normalmente
        filterChain.doFilter(request, response);
    }
}
