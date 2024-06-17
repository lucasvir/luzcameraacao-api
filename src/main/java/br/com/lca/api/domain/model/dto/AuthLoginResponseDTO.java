package br.com.lca.api.domain.model.dto;

public record AuthLoginResponseDTO(
        String token,
        Long expiresIn
) {
}
