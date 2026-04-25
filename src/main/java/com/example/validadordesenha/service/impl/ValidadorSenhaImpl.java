package com.example.validadordesenha.service.impl;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;
import com.example.validadordesenha.service.ValidadorSenha;
import com.example.validadordesenha.service.validator.ValidadorDeRegras;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidadorSenhaImpl implements ValidadorSenha {

    private final List<ValidadorDeRegras> regrasValidacao;

    @Override
    public SenhaOutput validar(SenhaInput input) {
        String senha = input.senha();

        regrasValidacao.forEach(regra -> regra.validar(senha));

        return new SenhaOutput(true, "Senha válida.");
    }
}