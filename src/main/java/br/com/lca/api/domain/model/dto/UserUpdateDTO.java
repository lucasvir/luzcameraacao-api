package br.com.lca.api.domain.model.dto;

public record UserUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String telephone,
        String cpf,
        String cnpj
) {
}
