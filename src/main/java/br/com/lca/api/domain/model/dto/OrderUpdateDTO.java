package br.com.lca.api.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderUpdateDTO(
        Long userId,
        String location,
        String complement,
        String city,
        String uf,
        LocalDateTime startedAt,
        LocalDateTime expiresAt,
        BigDecimal totalValue
) {
}
