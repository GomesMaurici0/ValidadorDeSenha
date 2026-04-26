package com.example.validadordesenha.exception;

import com.example.validadordesenha.dto.SenhaOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<SenhaOutput> tratarValidacaoException(ValidacaoException ex) {
        SenhaOutput saida = new SenhaOutput(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saida);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SenhaOutput> tratarMetodoArgumentoInvalidoException() {
        SenhaOutput saida = new SenhaOutput(false, "A senha não pode ser vazia ou nula.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saida);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SenhaOutput> tratarExcecaoGenerica() {
        SenhaOutput saida = new SenhaOutput(false, "Erro interno do servidor ao processar a requisição.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(saida);
    }
}