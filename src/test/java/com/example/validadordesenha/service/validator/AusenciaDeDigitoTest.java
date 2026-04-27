package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AusenciaDeDigitoTest {
    private final AusenciaDeDigito validador = new AusenciaDeDigito();

    @Test
    @DisplayName("Deve lançar exceção quando senha não tiver dígito")
    void deveLancarExcecaoQuandoNaoTiverDigito() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("AbcDefGhi"));
        assertEquals(CodigoErro.DIGITO_AUSENTE, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando senha tiver dígito")
    void naoDeveLancarExcecaoQuandoTiverDigito() {
        assertDoesNotThrow(() -> validador.validar("AbcDefGhi1"));
    }
}