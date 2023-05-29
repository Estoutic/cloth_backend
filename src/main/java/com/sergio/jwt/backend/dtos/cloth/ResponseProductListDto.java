package com.sergio.jwt.backend.dtos.cloth;

import java.util.List;

public class ResponseProductListDto {


    public List<ProductListDto> lists;

    public ResponseProductListDto(List<ProductListDto> lists) {
        this.lists = lists;
    }
}
