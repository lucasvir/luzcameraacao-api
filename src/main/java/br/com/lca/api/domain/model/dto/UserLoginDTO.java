package br.com.lca.api.domain.model.dto;

public record UserLoginDTO(
        String email,
        String password
) {
}
