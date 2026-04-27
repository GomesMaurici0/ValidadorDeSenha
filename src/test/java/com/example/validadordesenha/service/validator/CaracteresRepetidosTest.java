package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.impl.CaracteresRepetidos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaracteresRepetidosTest {
    private final CaracteresRepetidos validador = new CaracteresRepetidos();

    @Test
    @DisplayName("Deve lançar exceção quando houver caracteres repetidos, exceto espaço")
    void deveLancarExcecaoQuandoCaracteresRepetidos() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("abcda"));
        assertEquals(CodigoErro.CARACTERES_REPETIDOS, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando não houver repetidos")
    void naoDeveLancarExcecaoQuandoNaoHaRepetidos() {
        assertDoesNotThrow(() -> validador.validar("abcde"));
    }

    @Test
    @DisplayName("Deve ignorar espaços ao validar repetição")
    void deveIgnorarEspacos() {
        assertDoesNotThrow(() -> validador.validar("abc de"));
        assertDoesNotThrow(() -> validador.validar("a b c d e"));
    }

    @Test
    @DisplayName("Deve lançar exceção para caracteres especiais repetidos")
    void deveDetectarRepeticaoDeCaractereEspecial() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("abc!!de"));
        assertEquals(CodigoErro.CARACTERES_REPETIDOS, ex.getCodigoErro());
    }
}
