package br.com.lca.api.domain.model.dto;

import br.com.lca.api.domain.model.Order;
import br.com.lca.api.domain.model.User;

import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String telephone,
        String cpf,
        String cnpj,
        List<Order> orders
) {
    public UserDTO(User user) {
        this(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTelephone(),
                user.getCpf(),
                user.getCnpj(),
                user.getOrders()
        );
    }
}
