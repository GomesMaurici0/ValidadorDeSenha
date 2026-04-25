package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.ValidacaoException;

public interface ValidadorDeRegras {

    void validar(String senha) throws ValidacaoException;
}