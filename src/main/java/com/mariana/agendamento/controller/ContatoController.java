package com.mariana.agendamento.controller;

import com.mariana.agendamento.model.Contato;
import com.mariana.agendamento.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Long id) {
        return contatoRepository.findById(id)
                .map(contato -> ResponseEntity.ok(contato))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contato criar(@RequestBody Contato contato) {
        return contatoRepository.save(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody Contato contatoAtualizado) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contato.setNome(contatoAtualizado.getNome());
                    contato.setEmail(contatoAtualizado.getEmail());
                    contato.setCelular(contatoAtualizado.getCelular());
                    contato.setTelefone(contatoAtualizado.getTelefone());
                    contato.setFavorito(contatoAtualizado.getFavorito());
                    contato.setAtivo(contatoAtualizado.getAtivo());
                    return ResponseEntity.ok(contatoRepository.save(contato));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (contatoRepository.existsById(id)) {
            contatoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
