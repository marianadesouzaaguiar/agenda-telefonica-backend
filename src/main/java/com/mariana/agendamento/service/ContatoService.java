package com.mariana.agendamento.service;

import com.mariana.agendamento.model.Contato;
import com.mariana.agendamento.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato salvar(Contato contato) {
        if (repository.existsByCelular(contato.getCelular())) {
            throw new RuntimeException("Contato já cadastrado com este celular.");
        }
        contato.setDataCadastro(LocalDateTime.now());
        contato.setAtivo("S");
        contato.setFavorito("N");
        return repository.save(contato);
    }

    public List<Contato> listar() {
        return repository.findAll();
    }

    public Contato atualizar(Long id, Contato atualizado) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado para atualização"));
        contato.setNome(atualizado.getNome());
        contato.setEmail(atualizado.getEmail());
        contato.setTelefone(atualizado.getTelefone());
        contato.setCelular(atualizado.getCelular());
        return repository.save(contato);
    }

    public void inativar(Long id) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado para inativar"));
        contato.setAtivo("N");
        repository.save(contato);
    }

    public void marcarFavorito(Long id) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado para marcar favorito"));
        contato.setFavorito(contato.getFavorito().equals("S") ? "N" : "S");
        repository.save(contato);
    }
}
