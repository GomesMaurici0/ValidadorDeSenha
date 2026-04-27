package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.ValidacaoException;

public interface RegraDaSenha {
    void validar(String senha) throws ValidacaoException;
}