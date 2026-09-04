package com.marcus.template.shared.api.exception;

import com.marcus.template.shared.exception.*;
import com.marcus.template.shared.api.exception.dto.ApiExceptionResponse;
import com.marcus.template.shared.api.exception.dto.FieldErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiExceptionResponse> handleInvalidCredential(
            InvalidCredentialException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ApiExceptionResponse response = ApiExceptionResponse.generate(
                status,
                request,
                exception.getMessage()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ApiExceptionResponse> handleInvalidRefreshToken(
            InvalidRefreshTokenException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ApiExceptionResponse response = ApiExceptionResponse.generate(
                status,
                request,
                exception.getMessage()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiExceptionResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        ApiExceptionResponse response = ApiExceptionResponse.generate(
                status,
                request,
                exception.getMessage()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleUserNotFoundException(
            UserNotFoundException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiExceptionResponse response = ApiExceptionResponse.generate(
                status,
                request,
                exception.getMessage()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleRoleNotFoundException(
            RoleNotFoundException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiExceptionResponse response = ApiExceptionResponse.generate(
                status,
                request,
                exception.getMessage()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ApiExceptionResponse> validationHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<FieldErrorResponse> fieldErrorResponseList = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        error -> new FieldErrorResponse(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                ).toList();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiExceptionResponse response = ApiExceptionResponse.generateWithFields(
                status,
                request,
                "Already invalid fields.",
                fieldErrorResponseList
        );
        log.warn(
                "Falha na validação da requisição. method={}, path={}, errors={}",
                request.getMethod(),
                request.getRequestURI(),
                fieldErrorResponseList
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
