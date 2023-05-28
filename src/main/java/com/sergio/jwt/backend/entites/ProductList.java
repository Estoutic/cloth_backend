package com.sergio.jwt.backend.entites;

import com.sergio.jwt.backend.entites.cloth.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_list", uniqueConstraints = {@UniqueConstraint(columnNames={"user_id", "name"})})
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean deleted;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_list_product",
            joinColumns = {@JoinColumn(name = "product_list_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void addProduct(Product product) {
        this.getProducts().add(product);
        product.getProductLists().add(this);
    }

    public void removeProduct(Product product) {
        this.getProducts().remove(product);
        product.getProductLists().remove(this);
    }
    public ProductList(String name, User user) {
        this.name = name;
        this.deleted = false;
        this.user = user;
    }

}