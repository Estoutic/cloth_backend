package com.sergio.jwt.backend.dtos.tobacoo;

import com.sergio.jwt.backend.entites.tobacoo.Category;

public class CategoryDto {

    public String id;

    public String name;

    public CategoryDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
}
