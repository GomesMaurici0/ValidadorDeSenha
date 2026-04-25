package com.example.validadordesenha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SenhaOutput(
        @JsonProperty("valido")
        boolean valido,

        @JsonProperty("mensagem")
        String mensagem
) {}