package com.mariana.agendamento.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contato")
@Getter
@Setter
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contato_nome")
    private String nome;

    @Column(name = "contato_email")
    private String email;

    @Column(name = "contato_celular", unique = true)
    private String celular;

    @Column(name = "contato_telefone")
    private String telefone;

    @Column(name = "contato_sn_favorito")
    private String favorito;

    @Column(name = "contato_sn_ativo")
    private String ativo;

    @Column(name = "contato_dh_cad")
    private LocalDateTime dataCadastro;
}
