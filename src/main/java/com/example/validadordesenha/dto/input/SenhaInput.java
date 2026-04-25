package com.example.validadordesenha.dto.input;

import jakarta.validation.constraints.NotBlank;

public record SenhaInput(

        @NotBlank(message = "A senha não pode ser vazia")
        String senha
){}
