package com.sergio.jwt.backend.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "sur_name", nullable = false)
    @Size(max = 100)
    private String surName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(nullable = false)
    @Size(max = 100)
    private String phone;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<ProductList> productLists = new ArrayList<>();

}
