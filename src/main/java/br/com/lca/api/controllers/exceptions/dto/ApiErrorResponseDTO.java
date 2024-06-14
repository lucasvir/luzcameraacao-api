package br.com.lca.api.controllers.exceptions.dto;

import br.com.lca.api.controllers.exceptions.ApiError;

import java.util.Date;

public record ApiErrorResponseDTO(
        Date timestamp,
        String message,
        String status,
        Integer code
) {
    public ApiErrorResponseDTO(ApiError apiError) {
        this(
                apiError.getTimestamp(),
                apiError.getMessage(),
                apiError.getStatus(),
                apiError.getCode()
        );
    }
}
