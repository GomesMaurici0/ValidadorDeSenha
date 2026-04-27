package com.example.validadordesenha.exception;

public class ValidacaoException extends RuntimeException {

    private final CodigoErro codigoErro;

    public ValidacaoException(CodigoErro codigoErro) {
        super(codigoErro.getMensagem());
        this.codigoErro = codigoErro;
    }

    public CodigoErro getCodigoErro() {
        return codigoErro;
    }
}