package com.mariana.agendamento.service;

import com.mariana.agendamento.model.Contato;
import com.mariana.agendamento.repository.ContatoRepository;
import com.mariana.agendamento.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public List<Contato> getAllContatos() {
        return repository.findAll();
    }

    public Contato getContatoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id " + id));
    }

    public Contato createContato(Contato contato) {
        return repository.save(contato);
    }

    public Contato updateContato(Long id, Contato contatoDetails) {
        Contato contato = getContatoById(id);
        contato.setNome(contatoDetails.getNome());
        contato.setEmail(contatoDetails.getEmail());
        contato.setTelefone(contatoDetails.getTelefone());
        return repository.save(contato);
    }

    public void deleteContato(Long id) {
        Contato contato = getContatoById(id);
        repository.delete(contato);
    }
}
