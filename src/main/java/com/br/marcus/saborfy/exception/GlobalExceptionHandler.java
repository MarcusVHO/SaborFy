package com.br.marcus.saborfy.exception;

import com.br.marcus.saborfy.exception.exceptions.ConflictException;
import com.br.marcus.saborfy.exception.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.br.marcus.saborfy.exception.exceptions.BusinessException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Business Error",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConflictException(
            ConflictException ex,
            HttpServletRequest request
    ) {

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Conflict Error!",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotFoundException(
            NotFoundException ex,
            HttpServletRequest request
    ) {

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Not Found Error!",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        List<ApiErrorDetail> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ApiErrorDetail(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Erro de validação",
                request.getRequestURI(),
                details
        );
    }
}