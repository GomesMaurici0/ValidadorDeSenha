package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class AusenciaDeDigito implements ValidadorDeRegras {

    private static final String REGEX_DIGITO = ".*\\d.*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(REGEX_DIGITO)) {
            throw new ValidacaoException(CodigoErro.DIGITO_AUSENTE);
        }
    }
}