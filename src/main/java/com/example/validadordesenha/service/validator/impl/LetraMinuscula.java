package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import org.springframework.stereotype.Component;

@Component
public class LetraMinuscula implements RegraDaSenha {

    private static final String LETRA_MINUSCULA = ".*[a-z].*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(LETRA_MINUSCULA)) {
            throw new ValidacaoException(CodigoErro.LETRA_MINUSCULA_AUSENTE);
        }
    }
}