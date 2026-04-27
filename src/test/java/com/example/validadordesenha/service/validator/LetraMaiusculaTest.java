package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetraMaiusculaTest {
    private final LetraMaiuscula validador = new LetraMaiuscula();

    @Test
    @DisplayName("Deve lançar exceção quando senha não tiver letra maiúscula")
    void deveLancarExcecaoQuandoNaoTiverLetraMaiuscula() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("abcdefghi1!"));
        assertEquals(CodigoErro.LETRA_MAIUSCULA_AUSENTE, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando senha tiver letra maiúscula")
    void naoDeveLancarExcecaoQuandoTiverLetraMaiuscula() {
        assertDoesNotThrow(() -> validador.validar("Abcdefghi1!"));
    }
}