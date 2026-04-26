package com.example.validadordesenha.service;

import com.example.validadordesenha.dto.SenhaInput;
import com.example.validadordesenha.dto.SenhaOutput;

public interface ValidadorSenhaService {

    SenhaOutput validarSenha(SenhaInput input);
}