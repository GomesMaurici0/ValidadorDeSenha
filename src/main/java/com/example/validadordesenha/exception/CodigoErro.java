package com.example.validadordesenha.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodigoErro {

    CARACTER_INSUFICIENTE("A senha deve ter pelo menos 9 caracteres."),
    LETRA_MAIUSCULA_AUSENTE("A senha deve conter pelo menos uma letra maiúscula (A-Z)."),
    LETRA_MINUSCULA_AUSENTE("A senha deve conter pelo menos uma letra minúscula (a-z)."),
    DIGITO_AUSENTE("A senha deve conter pelo menos um dígito (0-9)."),
    CARACTERE_ESPECIAL_AUSENTE("A senha deve conter pelo menos um caractere especial (!@#$%^&*()-+)."),
    CARACTERES_REPETIDOS("A senha não pode conter caracteres repetidos.");

    private final String mensagem;
}