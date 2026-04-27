package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetraMinusculaTest {
    private final LetraMinuscula validador = new LetraMinuscula();

    @Test
    @DisplayName("Deve lançar exceção quando senha não tiver letra minúscula")
    void deveLancarExcecaoQuandoNaoTiverLetraMinuscula() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("ABCDEFGHI1!"));
        assertEquals(CodigoErro.LETRA_MINUSCULA_AUSENTE, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando senha tiver letra minúscula")
    void naoDeveLancarExcecaoQuandoTiverLetraMinuscula() {
        assertDoesNotThrow(() -> validador.validar("Abcdefghi1!"));
    }
}