package com.github.maikoncarlos.desafio_mergulho_rest_api.exceptionHandler;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.exception.BusinessEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAPI extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorStandard.Field> fields = new ArrayList<>();

        for(ObjectError errorObj : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) errorObj).getField();
            String message = messageSource.getMessage(errorObj, LocaleContextHolder.getLocale());

            fields.add( new ErrorStandard.Field(name, message));
        }

        ErrorStandard error = new ErrorStandard();
        error.setStatus(status.value());
        error.setDateTime(LocalDateTime.now());
        error.setTitle("Um ou mais campos estão inválidos!");
        error.setFields(fields);

        return handleExceptionInternal(ex, error, headers, status, request);
    }
    @ExceptionHandler(BusinessEmailException.class)
    public ResponseEntity<Object> handlerBusinessException(BusinessEmailException exception, HttpHeaders headers, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorStandard error = new ErrorStandard();
        error.setStatus(status.value());
        error.setDateTime(LocalDateTime.now());
        error.setTitle(exception.getMessage());

        return handleExceptionInternal(exception, error, headers, status, request);
    }
}