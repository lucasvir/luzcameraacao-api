package br.com.lca.api.domain.model.dto;

import java.math.BigDecimal;

public record ProductCreateDTO(
        Long orderId,
        String name,
        String brand,
        String category,
        String type,
        BigDecimal price
) {
}
