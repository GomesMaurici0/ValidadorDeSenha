package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import org.springframework.stereotype.Component;

@Component
public class AusenciaDeDigito implements RegraDaSenha {

    private static final String REGEX_DIGITO = ".*\\d.*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(REGEX_DIGITO)) {
            throw new ValidacaoException(CodigoErro.DIGITO_AUSENTE);
        }
    }
}