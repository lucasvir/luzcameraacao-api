package br.com.lca.api.domain.model.dto;

import br.com.lca.api.domain.model.Product;
import br.com.lca.api.domain.model.enums.Category;
import br.com.lca.api.domain.model.enums.Type;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        String brand,
        Category category,
        Type type,
        BigDecimal price,
        boolean isAvailable
) {
    public ProductDTO(Product productEntity) {
        this(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getBrand(),
                productEntity.getCategory(),
                productEntity.getType(),
                productEntity.getPrice(),
                productEntity.isAvailable()
        );
    }
}
