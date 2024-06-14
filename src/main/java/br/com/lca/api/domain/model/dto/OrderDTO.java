package br.com.lca.api.domain.model.dto;

import br.com.lca.api.domain.model.Order;
import br.com.lca.api.domain.model.Product;
import br.com.lca.api.domain.model.enums.UnidadeFederativa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long userId,
        String location,
        String complement,
        UnidadeFederativa uf,
        LocalDateTime startedAt,
        LocalDateTime expiresAt,
        BigDecimal totalValue,
        boolean isFinished,
        List<Product> products
) {
    public OrderDTO(Order order) {
        this(
                order.getId(),
                order.getUserId(),
                order.getLocation(),
                order.getComplement(),
                order.getUf(),
                order.getStartedAt(),
                order.getExpiresAt(),
                order.getTotalValue(),
                order.isFinished(),
                order.getProducts()
        );
    }
}
