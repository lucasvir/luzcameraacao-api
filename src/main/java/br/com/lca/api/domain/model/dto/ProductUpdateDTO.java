package br.com.lca.api.domain.model.dto;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        Long orderId,
        String name,
        String brand,
        String category,
        String type,
        BigDecimal price
) {
}
