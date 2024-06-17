package br.com.lca.api.domain.repositories;

import br.com.lca.api.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
