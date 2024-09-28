package br.com.ProjetoReal.TudoList.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // toda excessao que tiver, ela vaic passar por esse controller
public class ExceptionHandlerController {
    // metodo que vai interceptar e tratar as excessoes

    @ExceptionHandler(HttpMessageNotReadableException.class) // toda excessao desse tipo vai entrar aqui antes de ir ao usuario
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
        // aqui ele vai responder com um status 400 e a mensagem do erro
    }

}
