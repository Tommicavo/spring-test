package _rc.assessment.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
    private Long id;

    @Column(name = "name_product", unique = true)
    @NotBlank(message = "Name must not be empty")
    private String name;

    @Column(name = "description_product")
    @NotBlank(message = "Description must not be empty")
    private String description;

    @Column(name = "price_product")
    @NotNull(message = "Price must not be empty")
    @Min(value = 0, message = "Price must be greater than 0€")
    private double price;

    @Column(name = "quantity_product")
    @NotNull(message = "Quantity must not be empty")
    @Min(value = 0, message = "Quantity must be greater than 0")
    private Integer quantity;

    public Product() {
        this.quantity = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
