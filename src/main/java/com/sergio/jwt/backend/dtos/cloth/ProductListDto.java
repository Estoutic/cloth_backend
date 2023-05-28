package com.sergio.jwt.backend.dtos.cloth;

import com.sergio.jwt.backend.entites.cloth.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {

    public String name;

    public List<ProductDto> products;
}
