package com.mariana.agendamento.controller;

import com.mariana.agendamento.model.Contato;
import com.mariana.agendamento.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping
    public ResponseEntity<Contato> criar(@RequestBody Contato contato) {
        return ResponseEntity.ok(service.salvar(contato));
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody Contato contato) {
        return ResponseEntity.ok(service.atualizar(id, contato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/favorito")
    public ResponseEntity<Void> favoritar(@PathVariable Long id) {
        service.marcarFavorito(id);
        return ResponseEntity.noContent().build();
    }
}
