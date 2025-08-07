package com.mariana.agendamento.security;

import com.mariana.agendamento.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "secretkey1234567890"; // sua chave secreta, em produção use config segura
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em ms

    public String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
