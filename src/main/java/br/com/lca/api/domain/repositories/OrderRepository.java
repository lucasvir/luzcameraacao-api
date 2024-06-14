package br.com.lca.api.domain.repositories;

import br.com.lca.api.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
