package br.com.lca.api.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderCreateDTO(
        Long userId,
        String location,
        String complement,
        String uf,
        LocalDateTime startedAt,
        LocalDateTime expiresAt,
        BigDecimal totalValue,
        List<Long> productsId
) {
}
