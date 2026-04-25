package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class CaractereEspecial implements ValidadorDeRegras {

    private static final String REGEX_CARACTERE_ESPECIAL = ".*[!@#$%^&*()-+].*";

    @Override
    public void validar(String senha) throws ValidacaoException {
        if (!senha.matches(REGEX_CARACTERE_ESPECIAL)) {
            throw new ValidacaoException(CodigoErro.CARACTERE_ESPECIAL_AUSENTE);
        }
    }
}