package com.mariana.agendamento.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String celular;
    private String email;
    private String telefone;

    // Armazenado como "S" ou "N" no banco
    @Column(length = 1)
    private String favorito = "N";

    @Column(length = 1)
    private String ativo = "S";
}
