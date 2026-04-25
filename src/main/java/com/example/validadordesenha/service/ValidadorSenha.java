package com.example.validadordesenha.service;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;

public interface ValidadorSenha {

    SenhaOutput validar(SenhaInput input);
}