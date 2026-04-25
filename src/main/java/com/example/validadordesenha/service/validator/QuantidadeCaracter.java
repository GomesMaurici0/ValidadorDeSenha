package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class QuantidadeCaracter implements ValidadorDeRegras {

    private static final int QUANTIDADE_MINIMA = 9;

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (senha.length() < QUANTIDADE_MINIMA) {
            throw new ValidacaoException(CodigoErro.CARACTER_INSUFICIENTE);
        }
    }
}