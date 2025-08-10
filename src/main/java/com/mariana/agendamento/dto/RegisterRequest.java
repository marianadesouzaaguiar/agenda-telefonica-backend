package com.mariana.agendamento.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String dataNascimento;
}
