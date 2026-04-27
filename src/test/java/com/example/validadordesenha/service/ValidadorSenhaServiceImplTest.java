package com.example.validadordesenha.service;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.impl.ValidadorSenhaServiceImpl;
import com.example.validadordesenha.service.validator.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorSenhaServiceImplTest {

    private ValidadorSenhaService service;

    @BeforeEach
    void setUp() {
        service = new ValidadorSenhaServiceImpl(
                List.of(
                        new QuantidadeCaracter(),
                        new LetraMaiuscula(),
                        new LetraMinuscula(),
                        new AusenciaDeDigito(),
                        new AusenciaDeEspaco(),
                        new CaractereEspecial(),
                        new CaracteresRepetidos()
                )
        );
    }

    @Test
    void deveRetornarSenhaValida() {
        SenhaInput input = new SenhaInput("AbTp9!fok");
        SenhaOutput output = service.validarSenha(input);

        assertTrue(output.valido());
        assertEquals("Senha válida.", output.mensagem());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaTiverMenosDe9Caracteres() {
        SenhaInput input = new SenhaInput("AbTp9!fo");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNaoTiverLetraMaiuscula() {
        SenhaInput input = new SenhaInput("abtp9!fok");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNaoTiverLetraMinuscula() {
        SenhaInput input = new SenhaInput("ABTP9!FOK");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNaoTiverDigito() {
        SenhaInput input = new SenhaInput("AbTp!fok");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNaoTiverCaractereEspecial() {
        SenhaInput input = new SenhaInput("AbTp9fok");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaTiverCaracteresRepetidos() {
        SenhaInput input = new SenhaInput("AbTp9!foo");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaTiverEspaco() {
        SenhaInput input = new SenhaInput("AbTp9!fok ");
        assertThrows(ValidacaoException.class, () -> service.validarSenha(input));
    }
}