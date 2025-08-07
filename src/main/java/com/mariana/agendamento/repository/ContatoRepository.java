package com.mariana.agendamento.repository;

import com.mariana.agendamento.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    boolean existsByCelular(String celular);
}

