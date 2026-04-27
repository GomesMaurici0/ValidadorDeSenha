package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CaracteresRepetidos implements RegraDaSenha {

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (possuiCaracteresRepetidos(senha)) {
            throw new ValidacaoException(CodigoErro.CARACTERES_REPETIDOS);
        }
    }

    private boolean possuiCaracteresRepetidos(String senha) {
        Set<Character> caracteres = new HashSet<>();
        for (char c : senha.toCharArray()) {
            if (c == ' ') continue;  // Ignora espaços
            if (!caracteres.add(c)) {
                return true;
            }
        }
        return false;
    }

}