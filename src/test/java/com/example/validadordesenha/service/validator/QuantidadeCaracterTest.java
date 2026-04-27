package com.example.validadordesenha.service.validator;

import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.validator.impl.QuantidadeCaracter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantidadeCaracterTest {
    private final QuantidadeCaracter validador = new QuantidadeCaracter();

    @Test
    @DisplayName("Deve lançar exceção quando senha tiver menos de 9 caracteres")
    void deveLancarExcecaoQuandoTiverMenosDe9Caracteres() {
        ValidacaoException ex = assertThrows(ValidacaoException.class, () ->
                validador.validar("AbTp9!fo"));
        assertEquals(CodigoErro.TAMANHO_INSUFICIENTE, ex.getCodigoErro());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando senha tiver 9 ou mais caracteres")
    void naoDeveLancarExcecaoQuandoTiver9OuMaisCaracteres() {
        assertDoesNotThrow(() -> validador.validar("AbTp9!fok"));
        assertDoesNotThrow(() -> validador.validar("AbTp9!fok1"));
    }
}