package br.com.lca.api.controllers.exceptions;

import org.springframework.http.HttpStatus;

public class EmptyResourceException extends RuntimeException {

    private final HttpStatus status;

    public EmptyResourceException() {
        super("Empty resource.");
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
