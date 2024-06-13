package br.com.lca.api.domain.model;

import br.com.lca.api.domain.model.enums.UnidadeFederativa;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int complement;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeFederativa uf;

    @Column(nullable = false)
    private LocalDateTime startedAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false, scale = 2, precision = 13)
    private BigDecimal totalValue;

    @Column(nullable = false)
    private boolean isFinished;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public Order() {
    }

    public Order(Long userId, String location, int complement, UnidadeFederativa uf, LocalDateTime startedAt, LocalDateTime expiresAt, BigDecimal totalValue, List<Product> products) {
        this.userId = userId;
        this.location = location;
        this.complement = complement;
        this.uf = uf;
        this.startedAt = startedAt;
        this.expiresAt = expiresAt;
        this.totalValue = totalValue;
        this.products = products;
        this.isFinished = false;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLocation() {
        return location;
    }

    public int getComplement() {
        return complement;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", location='" + location + '\'' +
                ", complement=" + complement +
                ", uf=" + uf +
                ", startedAt=" + startedAt +
                ", expiresAt=" + expiresAt +
                ", totalValue=" + totalValue +
                ", isFinished=" + isFinished +
                ", products=" + products +
                '}';
    }
}
