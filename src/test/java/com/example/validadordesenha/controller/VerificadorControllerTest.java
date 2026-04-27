package com.example.validadordesenha.controller;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;
import com.example.validadordesenha.exception.CodigoErro;
import com.example.validadordesenha.exception.ValidacaoException;
import com.example.validadordesenha.service.ValidadorSenhaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VerificadorController.class)
class VerificadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValidadorSenhaService validadorSenhaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 200 quando senha for válida")
    void deveRetornar200QuandoSenhaForValida() throws Exception {
        SenhaInput input = new SenhaInput("AbTp9!fok");

        when(validadorSenhaService.validarSenha(any(SenhaInput.class)))
                .thenReturn(new SenhaOutput(true, "Senha válida."));

        mockMvc.perform(post("/v1/verificador-senha/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valido").value(true))
                .andExpect(jsonPath("$.mensagem").value("Senha válida."));
    }

    @Test
    @DisplayName("Deve retornar 400 quando ocorrer erro de validação")
    void deveRetornar400QuandoValidacaoException() throws Exception {
        SenhaInput input = new SenhaInput("abc");

        when(validadorSenhaService.validarSenha(any(SenhaInput.class)))
                .thenThrow(new ValidacaoException(CodigoErro.LETRA_MAIUSCULA_AUSENTE));

        mockMvc.perform(post("/v1/verificador-senha/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valido").value(false))
                .andExpect(jsonPath("$.mensagem")
                        .value(containsString("letra maiúscula")));
    }

    @Test
    @DisplayName("Deve retornar 400 quando input for inválido (senha nula ou vazia)")
    void deveRetornar400QuandoInputInvalido() throws Exception {

        mockMvc.perform(post("/v1/verificador-senha/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valido").value(false))
                .andExpect(jsonPath("$.mensagem")
                        .value("A senha não pode ser vazia ou nula."));
    }
}