package com.sergio.jwt.backend.dtos.cloth;

import com.sergio.jwt.backend.entites.ProductList;

public class ProductListNamesDto {


    public Long id;

    public String name;


    public ProductListNamesDto(ProductList productList) {
        this.id = productList.getId();
        this.name = productList.getName();
    }
}
