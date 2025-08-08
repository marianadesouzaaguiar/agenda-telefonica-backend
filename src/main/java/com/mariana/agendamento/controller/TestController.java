package com.mariana.agendamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/auth-test")
    public String test() {
        System.out.println("Test endpoint acessado!");
        return "API funcionando sem Spring Security!";
    }
}
