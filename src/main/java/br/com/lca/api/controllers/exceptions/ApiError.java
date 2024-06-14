package br.com.lca.api.controllers.exceptions;

import br.com.lca.api.controllers.exceptions.dto.ApiErrorResponseDTO;
import br.com.lca.api.controllers.exceptions.dto.ErrorDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ApiError {

    private final Date timestamp;
    private final String message;
    private final String status;
    private final Integer code;


    public ApiError(ErrorDto error) {
        this.timestamp = new Date();
        this.status = error.status().name();
        this.code = error.status().value();
        this.message = error.message();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseEntity<ApiErrorResponseDTO> createResponse() {
        ApiErrorResponseDTO apiError = new ApiErrorResponseDTO(
                this.timestamp,
                this.message,
                this.status,
                this.code
        );

        return ResponseEntity.status(getCode()).body(apiError);
    }
}
