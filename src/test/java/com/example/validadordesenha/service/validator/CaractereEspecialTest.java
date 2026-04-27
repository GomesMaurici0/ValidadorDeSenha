package com.example.validadordesenha.service.validator.impl;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaractereEspecialTest {
    private final CaractereEspecial validador = new CaractereEspecial();

    @Test
    @DisplayName("Deve lançar exceção quando senha não tiver caractere especial")
    void deveLancarExcecaoQuandoNaoTiverCaractereEspecial() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("AbTp9fok"));
        assertEquals(CodigoErro.CARACTERE_ESPECIAL_AUSENTE, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando senha tiver caractere especial")
    void naoDeveLancarExcecaoQuandoTiverCaractereEspecial() {
        assertDoesNotThrow(() -> validador.validar("AbTp9!fok"));
    }
}