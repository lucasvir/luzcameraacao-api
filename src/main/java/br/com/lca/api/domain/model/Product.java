package br.com.lca.api.domain.model;

import br.com.lca.api.domain.model.enums.Category;
import br.com.lca.api.domain.model.enums.Type;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isAvaiable;

    public Product() {
    }

    public Product(Long orderId, String name, String brand, Category category, Type type, BigDecimal price) {
        this.orderId = orderId;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.type = type;
        this.price = price;
        this.isAvaiable = false;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvaiable() {
        return isAvaiable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}


