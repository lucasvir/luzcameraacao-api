package br.com.lca.api.domain.model;

import br.com.lca.api.controllers.exceptions.EmptyDataTransferObject;
import br.com.lca.api.domain.model.dto.OrderUpdateDTO;
import br.com.lca.api.domain.model.enums.UnidadeFederativa;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String complement;

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

    public Order(Long userId, String location, String complement, UnidadeFederativa uf, LocalDateTime startedAt, LocalDateTime expiresAt, BigDecimal totalValue) {
        this.userId = userId;
        this.location = location;
        this.complement = complement;
        this.uf = uf;
        this.startedAt = startedAt;
        this.expiresAt = expiresAt;
        this.totalValue = totalValue;
        this.products = new ArrayList<>();
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

    public String getComplement() {
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

    public void updateData(OrderUpdateDTO updateDTO) {
        boolean emptyDTO =
                updateDTO.userId() == null
                        && updateDTO.location() == null
                        && updateDTO.complement() == null
                        && updateDTO.uf() == null
                        && updateDTO.startedAt() == null
                        && updateDTO.expiresAt() == null
                        && updateDTO.totalValue() == null;

        if (emptyDTO) throw new EmptyDataTransferObject();

        boolean userIdIsPresent = updateDTO.userId() != null && updateDTO.userId() > 0;
        boolean locationIsPresent = updateDTO.location() != null && !updateDTO.location().isBlank();
        boolean complementIsPresent = updateDTO.complement() != null && !updateDTO.complement().isBlank();
        boolean ufIsPresent = updateDTO.uf() != null && !updateDTO.uf().isBlank();
        boolean startedAtIsPresent = updateDTO.startedAt() != null && !updateDTO.startedAt().toString().isBlank();
        boolean expiresAtIsPresent = updateDTO.expiresAt() != null && !updateDTO.expiresAt().toString().isBlank();
        boolean totalIsPresent = updateDTO.totalValue() != null && updateDTO.totalValue().doubleValue() > 0;


        if (userIdIsPresent) this.userId = updateDTO.userId();
        if (locationIsPresent) this.location = updateDTO.location();
        if (complementIsPresent) this.complement = updateDTO.complement();
        if (ufIsPresent) this.uf = UnidadeFederativa.fromSigla(updateDTO.uf());
        if (startedAtIsPresent) this.startedAt = updateDTO.startedAt();
        if (expiresAtIsPresent) this.expiresAt = updateDTO.expiresAt();
        if (totalIsPresent) this.totalValue = updateDTO.totalValue();
    }
}
