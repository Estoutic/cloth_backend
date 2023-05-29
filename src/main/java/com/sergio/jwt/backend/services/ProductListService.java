package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.cloth.ProductListDto;
import com.sergio.jwt.backend.dtos.cloth.ProductListNamesDto;
import com.sergio.jwt.backend.dtos.cloth.PurchaseDto;
import com.sergio.jwt.backend.dtos.cloth.ResponseProductListDto;

import java.util.List;

public interface ProductListService {

    void purchaseProducts(PurchaseDto purchaseDto,Long userId);

    boolean deleteListProducts(Long productListId, Long userId);

    ResponseProductListDto findUserProductList(Long userId);

    List<ProductListNamesDto> findUserProductListNames(Long userId);

    ProductListDto findProductList(String id);
}
