package com.example.validadordesenha.service.impl;

import com.example.validadordesenha.dto.input.SenhaInput;
import com.example.validadordesenha.dto.output.SenhaOutPut;
import com.example.validadordesenha.service.VerificadorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VerificadorServiceImpl implements VerificadorService {


    @Override
    public SenhaOutPut validaSenha(SenhaInput request) {
            String senha = request.senha();
            List<String> erros = new ArrayList<>();

            if (senha.length() < 8) {
                erros.add("A senha deve ter pelo menos 8 caracteres.");
            }
            if (!senha.matches(".*[A-Z].*")) {
                erros.add("A senha deve conter pelo menos uma letra maiúscula.");
            }
            if (!senha.matches(".*[a-z].*")) {
                erros.add("A senha deve conter pelo menos uma letra minúscula.");
            }
            if (!senha.matches(".*\\d.*")) {
                erros.add("A senha deve conter pelo menos um dígito.");
            }
            if (!senha.matches(".*[!@#$%^&*()-+].*")) {
                erros.add("A senha deve conter pelo menos um caractere especial (!@#$%^&*()-+).");
            }
            if (temCaracteresRepetidos(senha)) {
                erros.add("A senha não pode conter caracteres repetidos.");
            }

            if (erros.isEmpty()) {
                return new SenhaOutPut(true, "Senha válida.");
            } else {
                return new SenhaOutPut(false, String.join(" ", erros));
            }
        }

        private boolean temCaracteresRepetidos(String senha) {
            for (int i = 0; i < senha.length(); i++) {
                for (int j = i + 1; j < senha.length(); j++) {
                    if (senha.charAt(i) == senha.charAt(j)) {
                        return true;
                    }
                }
            }
            return false;
        }
}
