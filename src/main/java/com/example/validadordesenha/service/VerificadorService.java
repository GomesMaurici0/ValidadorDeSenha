package com.example.validadordesenha.service;

import com.example.validadordesenha.dto.input.SenhaInput;
import com.example.validadordesenha.dto.output.SenhaOutPut;

public interface VerificadorService {
    SenhaOutPut validaSenha (SenhaInput request);
}
