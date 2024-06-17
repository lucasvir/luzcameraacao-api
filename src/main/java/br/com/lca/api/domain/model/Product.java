package br.com.lca.api.domain.model;

import br.com.lca.api.controllers.exceptions.EmptyDataTransferObject;
import br.com.lca.api.domain.model.dto.ProductUpdateDTO;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isAvailable;

    public Product() {
    }

    public Product(String name, String brand, Category category, Type type, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    public Long getId() {
        return id;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    public void updateData(ProductUpdateDTO updateDTO) {
        boolean emptyDTO =
                updateDTO.orderId() == null
                        && updateDTO.name() == null
                        && updateDTO.brand() == null
                        && updateDTO.category() == null
                        && updateDTO.type() == null
                        && updateDTO.price() == null;

        if (emptyDTO) throw new EmptyDataTransferObject();

        boolean nameIsPresent = updateDTO.name() != null && !updateDTO.name().isBlank();
        boolean brandIsPresent = updateDTO.brand() != null && !updateDTO.brand().isBlank();
        boolean categoryIsPresent = updateDTO.category() != null && !updateDTO.category().isBlank();
        boolean typeIsPresent = updateDTO.type() != null && !updateDTO.type().isBlank();
        boolean priceIsPresent = updateDTO.price() != null && updateDTO.price().doubleValue() > 0.0;

        if (nameIsPresent) this.name = updateDTO.name();
        if (brandIsPresent) this.brand = updateDTO.brand();
        if (categoryIsPresent) this.category = Category.fromCode(updateDTO.category());
        if (typeIsPresent) this.type = Type.fromCode(updateDTO.type());
        if (priceIsPresent) this.price = updateDTO.price();
    }
}


