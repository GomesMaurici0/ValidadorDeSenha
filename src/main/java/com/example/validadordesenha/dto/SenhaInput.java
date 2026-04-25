package com.example.validadordesenha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SenhaInput(
        @JsonProperty("senha")
        @NotBlank
        String senha
) {}