package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AusenciaDeEspacoTest {
    private final AusenciaDeEspaco validador = new AusenciaDeEspaco();

    @Test
    @DisplayName("Deve lançar exceção quando senha tiver espaço")
    void deveLancarExcecaoQuandoTiverEspaco() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("abc def"));
        assertEquals(CodigoErro.EXISTENCIA_DE_ESPACO, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando senha não tiver espaço")
    void naoDeveLancarExcecaoQuandoNaoTiverEspaco() {
        assertDoesNotThrow(() -> validador.validar("abcdef"));
    }
}