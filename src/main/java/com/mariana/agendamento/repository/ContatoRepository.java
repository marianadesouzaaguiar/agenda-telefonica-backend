package com.mariana.agendamento.repository;

import com.mariana.agendamento.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findByCelular(String celular);
    boolean existsByCelular(String celular);
    List<Contato> findByAtivo(String ativo);
}
