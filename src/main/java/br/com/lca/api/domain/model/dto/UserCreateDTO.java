package br.com.lca.api.domain.model.dto;

public record UserCreateDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String telephone
) {
}
