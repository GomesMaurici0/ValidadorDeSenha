package com.example.validadordesenha.dto;

public record SenhaOutput(
        boolean valido,

        String mensagem
) {}