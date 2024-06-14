package br.com.lca.api.controllers.exceptions;

import org.springframework.http.HttpStatus;

public class EmptyDataTransferObject extends RuntimeException {

    private final HttpStatus status;

    public EmptyDataTransferObject() {
        super("Empty input arguments.");
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
