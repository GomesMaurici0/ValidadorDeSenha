package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import org.springframework.stereotype.Component;

@Component
public class CaractereEspecial implements RegraDaSenha {

    private static final String CARACTERE_ESPECIAL = ".*[!@#$%^&*()-+].*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(CARACTERE_ESPECIAL)) {
            throw new ValidacaoException(CodigoErro.CARACTERE_ESPECIAL_AUSENTE);
        }
    }
}