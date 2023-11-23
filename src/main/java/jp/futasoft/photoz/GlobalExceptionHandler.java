package jp.futasoft.photoz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PhotoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePhotoNotFoundException(PhotoNotFoundException ex) {
        return ex.getMessage();
    }
}
