package br.com.alelo.estrutura.controllers;

import br.com.alelo.estrutura.exceptions.NotFoundException;
import br.com.alelo.estrutura.vos.HttpResponseVO;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
@Log4j2
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private ObjectError error;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.log(Level.ERROR, "" + ex.getMessage(), ex);
        return new ResponseEntity(HttpResponseVO.builder().message("Internal Server error").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({HttpMessageConversionException.class, IllegalArgumentException.class})
    public final ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request) {
        log.log(Level.WARN, "" + ex.getMessage(), ex);

        return new ResponseEntity(HttpResponseVO.builder().message("Request is invalid").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.log(Level.ERROR,  e.getMessage(), e);

        return new ResponseEntity(HttpResponseVO.builder().message("Internal Server error").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
        log.log(Level.INFO,  ex.getMessage(), ex);
        return new ResponseEntity(HttpResponseVO.builder().message("The resource was not found.").build(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        return new ResponseEntity(HttpResponseVO.builder().message("Argument validation failed.").build(), HttpStatus.BAD_REQUEST);
    }

}
