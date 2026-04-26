package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import org.springframework.stereotype.Component;

@Component
public class LetraMaiuscula implements RegraDaSenha {

    private static final String REGEX_LETRA_MAIUSCULA = ".*[A-Z].*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(REGEX_LETRA_MAIUSCULA)) {
            throw new ValidacaoException(CodigoErro.LETRA_MAIUSCULA_AUSENTE);
        }
    }
}