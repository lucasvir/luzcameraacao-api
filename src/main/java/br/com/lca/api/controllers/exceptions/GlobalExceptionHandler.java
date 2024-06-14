package br.com.lca.api.controllers.exceptions;

import br.com.lca.api.controllers.exceptions.dto.ApiErrorResponseDTO;
import br.com.lca.api.controllers.exceptions.dto.ErrorDto;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponseDTO> handle404WhenEntityNotFoundById(NoSuchElementException ex) {
        ErrorDto error = new ErrorDto("Not found. Argument: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        ApiError apiError = new ApiError(error);

        return apiError.createResponse();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponseDTO> handle400IllegalArgumentException(IllegalArgumentException ex) {
        ErrorDto error = new ErrorDto("Illegal argument. Argument: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        ApiError apiError = new ApiError(error);

        return apiError.createResponse();
    }

    @ExceptionHandler(EmptyResourceException.class)
    public ResponseEntity<ApiErrorResponseDTO> handle404EmptyResourceException(EmptyResourceException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), ex.getStatus());
        ApiError apiError = new ApiError(error);

        return apiError.createResponse();
    }

    @ExceptionHandler(EmptyDataTransferObject.class)
    public ResponseEntity<ApiErrorResponseDTO> handle400EmptyDataTransferObject(EmptyDataTransferObject ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), ex.getStatus());
        ApiError apiError = new ApiError(error);

        return apiError.createResponse();
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ApiErrorResponseDTO> handle400PropertyValueException(PropertyValueException ex) {
        ErrorDto error = new ErrorDto("Illegal property value. Property: " + ex.getPropertyName(), HttpStatus.BAD_REQUEST);
        ApiError apiError = new ApiError(error);

        return apiError.createResponse();
    }
}
