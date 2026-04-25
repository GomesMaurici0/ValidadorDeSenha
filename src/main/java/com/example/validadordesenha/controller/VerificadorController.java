package com.example.validadordesenha.controller;

import com.example.validadordesenha.dto.input.SenhaInput;
import com.example.validadordesenha.dto.output.SenhaOutPut;
import com.example.validadordesenha.service.VerificadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/verificador-de-senha/")
@RequiredArgsConstructor
public class VerificadorController {

    private final VerificadorService service;

    @PostMapping("valida")
    public ResponseEntity<SenhaOutPut> validaSenha(@Valid @RequestBody SenhaInput request) {
        SenhaOutPut response = service.validaSenha(request);
        return ResponseEntity.ok(response);
    }
}
