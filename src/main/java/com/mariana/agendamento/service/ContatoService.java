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

    public Contato buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

    public Contato atualizar(Long id, Contato atualizado) {
        Contato contato = buscarPorId(id);
        contato.setNome(atualizado.getNome());
        contato.setEmail(atualizado.getEmail());
        contato.setTelefone(atualizado.getTelefone());
        contato.setCelular(atualizado.getCelular());
        contato.setFavorito(atualizado.getFavorito());
        contato.setAtivo(atualizado.getAtivo());
        return repository.save(contato);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Contato não encontrado para deletar");
        }
        repository.deleteById(id);
    }

    public Contato inativar(Long id) {
        Contato contato = buscarPorId(id);
        contato.setAtivo("N");
        return repository.save(contato);
    }

    public Contato marcarFavorito(Long id) {
        Contato contato = buscarPorId(id);
        contato.setFavorito(contato.getFavorito().equals("S") ? "N" : "S");
        return repository.save(contato);
    }

}
