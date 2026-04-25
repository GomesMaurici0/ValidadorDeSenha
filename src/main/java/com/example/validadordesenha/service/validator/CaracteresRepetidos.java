package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class CaracteresRepetidos implements ValidadorDeRegras {

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (possuiCaracteresRepetidos(senha)) {
            throw new ValidacaoException(CodigoErro.CARACTERES_REPETIDOS);
        }
    }

    private boolean possuiCaracteresRepetidos(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            for (int j = i + 1; j < senha.length(); j++) {
                if (senha.charAt(i) == senha.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}