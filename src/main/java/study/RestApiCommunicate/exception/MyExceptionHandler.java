package study.RestApiCommunicate.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class MyExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String errorHandler(IllegalArgumentException e){
        return e.getMessage();

    }
}
