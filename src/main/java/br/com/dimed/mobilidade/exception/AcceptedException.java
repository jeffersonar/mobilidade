package br.com.dimed.mobilidade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED)
public class AcceptedException extends RuntimeException{
    
     public AcceptedException(String mensagem) {
        super(mensagem);
    }
}