package com.mariana.agendamento.repository;

import com.mariana.agendamento.entity.Contato;
import com.mariana.agendamento.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByUser(User user);
}
