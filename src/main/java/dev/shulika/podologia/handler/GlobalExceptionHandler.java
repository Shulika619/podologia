package dev.shulika.podologia.handler;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.ErrorDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        log.warn("IN GlobalExceptionHandler - MethodArgumentNotValidException: {}", exception);
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleProductNotFoundException(ObjectNotFoundException exception) {
        log.warn("IN GlobalExceptionHandler - ObjectNotFoundException: {}", exception);
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO(exception.getField(), exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(ServiceBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleServiceException(ServiceBusinessException exception) {
        log.warn("IN GlobalExceptionHandler - ServiceBusinessException: {}", exception);
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO(exception.getField(), exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleTransientPropertyValueException(TransientPropertyValueException exception) {
        log.warn("IN GlobalExceptionHandler - TransientPropertyValueException: {}", exception);
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO(exception.getPropertyName(), "Read the API documentation and check request data")));
        return serviceResponse;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.warn("IN GlobalExceptionHandler - DataIntegrityViolationException: {}", exception);
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO(exception.getMostSpecificCause().toString(), exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<?> handleAccessDenied(AccessDeniedException exception) {
        log.warn("IN GlobalExceptionHandler - AccessDeniedException");
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("FORBIDDEN", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleAuthentication(AuthenticationException exception) {
        log.warn("IN GlobalExceptionHandler - AuthenticationException: {}", exception);
        ApiResponse<?> serviceResponse = new ApiResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("Authentication.", exception.getMessage())));
        return serviceResponse;
    }
}
