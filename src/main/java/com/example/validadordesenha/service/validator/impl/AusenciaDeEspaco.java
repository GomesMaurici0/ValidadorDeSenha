package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.RegraDaSenha;
import org.springframework.stereotype.Component;

@Component
public class AusenciaDeEspaco implements RegraDaSenha {

    @Override
    public void validar(String senha) throws ValidacaoException {
        validarSemEspaco(senha);
    }

    private void validarSemEspaco(String senha) throws ValidacaoException {
        if (senha.contains(" ")) {
            throw new ValidacaoException(CodigoErro.EXISTENCIA_DE_ESPACO);
        }
    }
}

