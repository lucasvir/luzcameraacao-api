package br.com.lca.api.controllers.exceptions.dto;

import org.springframework.http.HttpStatus;

public record ErrorDto(String message, HttpStatus status) {
}
