package com.mariana.agendamento.model;

import jakarta.persistence.*;

@Entity
public class Contato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters e setters
}
