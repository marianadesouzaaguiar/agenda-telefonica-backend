package com.mariana.agendamento.controller;

import com.mariana.agendamento.model.Contato;
import com.mariana.agendamento.repository.ContatoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoRepository contatoRepository;

    public ContatoController(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @GetMapping
    public List<Contato> listar() {
        return contatoRepository.findByAtivo("S");
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Contato contato) {
        if (contatoRepository.findByCelular(contato.getCelular()).isPresent()) {
            return ResponseEntity.badRequest().body("Já existe um contato com esse número de celular.");
        }
        return ResponseEntity.ok(contatoRepository.save(contato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Contato atualizado) {
        return contatoRepository.findById(id).map(contato -> {
            contato.setNome(atualizado.getNome());
            contato.setEmail(atualizado.getEmail());
            contato.setTelefone(atualizado.getTelefone());
            contato.setCelular(atualizado.getCelular());
            return ResponseEntity.ok(contatoRepository.save(contato));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        return contatoRepository.findById(id).map(contato -> {
            contato.setAtivo("N");
            return ResponseEntity.ok(contatoRepository.save(contato));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/favorito")
    public ResponseEntity<?> toggleFavorito(@PathVariable Long id) {
        return contatoRepository.findById(id).map(contato -> {
            contato.setFavorito("S".equals(contato.getFavorito()) ? "N" : "S");
            return ResponseEntity.ok(contatoRepository.save(contato));
        }).orElse(ResponseEntity.notFound().build());
    }
}
