package com.example.validadordesenha.service.impl;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;
import com.example.validadordesenha.service.ValidadorSenhaService;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidadorSenhaServiceImpl implements ValidadorSenhaService {

    private final List<RegraDaSenha> regras;

    @Override
    public SenhaOutput validarSenha(SenhaInput input) {
        String senha = input.senha();

        regras.forEach(regra -> regra.validar(senha));

        return new SenhaOutput(true, "Senha válida.");
    }
}