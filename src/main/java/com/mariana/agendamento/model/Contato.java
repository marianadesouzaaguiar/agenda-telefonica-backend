package com.mariana.agendamento.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "contato", schema = "desafio")
@Data
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;

    @Column(name = "contato_nome", length = 100)
    private String nome;

    @Column(name = "contato_email", length = 255)
    private String email;

    @Column(name = "contato_celular", length = 11, unique = true)
    private String celular;

    @Column(name = "contato_telefone", length = 10)
    private String telefone;

    @Column(name = "contato_sn_favorito", length = 1)
    private String favorito;

    @Column(name = "contato_sn_ativo", length = 1)
    private String ativo;

    @Column(name = "contato_dh_cad")
    private LocalDateTime dataCadastro;
}
