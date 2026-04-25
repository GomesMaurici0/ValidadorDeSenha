package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class LetraMaiuscula implements ValidadorDeRegras {

    private static final String REGEX_LETRA_MAIUSCULA = ".*[A-Z].*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(REGEX_LETRA_MAIUSCULA)) {
            throw new ValidacaoException(CodigoErro.LETRA_MAIUSCULA_AUSENTE);
        }
    }
}