package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.cloth.CategoryDto;
import com.sergio.jwt.backend.dtos.cloth.ProductDto;

import java.util.List;

public interface ProductService {

    String saveCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    String saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts(String categoryName);

}
