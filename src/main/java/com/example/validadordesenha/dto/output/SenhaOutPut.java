package com.example.validadordesenha.dto.output;

import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class SenhaOutPut {

    private Boolean valida;
    private String mensagem;

    public SenhaOutPut(boolean valida, String mensagem) {
        this.valida = valida;
        this.mensagem = mensagem;
    }
}
