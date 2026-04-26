package com.example.validadordesenha.controller;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;
import com.example.validadordesenha.service.ValidadorSenhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/verificador-senha")
@RequiredArgsConstructor
public class VerificadorController {

    private final ValidadorSenhaService validadorSenhaService;

    @PostMapping("/validar")
    public ResponseEntity<SenhaOutput> validar(@Valid @RequestBody SenhaInput request) {
        SenhaOutput response = validadorSenhaService.validarSenha(request);
        return ResponseEntity.ok(response);
    }
}