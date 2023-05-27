package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.tobacoo.CategoryDto;
import com.sergio.jwt.backend.dtos.tobacoo.ProductDto;

import java.util.List;

public interface ProductService {

    String saveCategory(String categoryName);

    List<CategoryDto> getAllCategories();

    String saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts(String categoryName);
}
