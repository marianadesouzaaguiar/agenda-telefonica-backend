package com.mariana.agendamento.controller;

import com.mariana.agendamento.model.Contato;
import com.mariana.agendamento.service.ContatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Contato> getAllContatos() {
        return service.getAllContatos();
    }

    @GetMapping("/{id}")
    public Contato getContatoById(@PathVariable Long id) {
        return service.getContatoById(id);
    }

    @PostMapping
    public Contato createContato(@RequestBody Contato contato) {
        return service.createContato(contato);
    }

    @PutMapping("/{id}")
    public Contato updateContato(@PathVariable Long id, @RequestBody Contato contato) {
        return service.updateContato(id, contato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        service.deleteContato(id);
        return ResponseEntity.noContent().build();
    }
}
