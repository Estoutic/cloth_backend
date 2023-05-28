package com.sergio.jwt.backend.services.impl;

import com.sergio.jwt.backend.dtos.cloth.ProductDto;
import com.sergio.jwt.backend.dtos.cloth.ProductListDto;
import com.sergio.jwt.backend.dtos.cloth.PurchaseDto;
import com.sergio.jwt.backend.dtos.cloth.ResponseProductListDto;
import com.sergio.jwt.backend.entites.ProductList;
import com.sergio.jwt.backend.entites.User;
import com.sergio.jwt.backend.entites.cloth.Product;
import com.sergio.jwt.backend.repositories.ProductListRepository;
import com.sergio.jwt.backend.repositories.ProductRepository;
import com.sergio.jwt.backend.repositories.UserRepository;
import com.sergio.jwt.backend.services.ProductListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListServiceImpl implements ProductListService {

    private final ProductListRepository productListRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductListServiceImpl(ProductListRepository productListRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.productListRepository = productListRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void purchaseProducts(PurchaseDto purchaseDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        ProductList productList = new ProductList(purchaseDto.getName(),user);

        List<Product> productsToAdd = new ArrayList<>();
        for (String id: purchaseDto.getProducts()){
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product does not exist"));
            productsToAdd.add(product);
        }

        for (Product product : productsToAdd) {
            if (productList.getProducts().contains(product)) {
                throw new RuntimeException("Product already exists in product list");
            }
            if (product.getCount() < 1) {
                throw new RuntimeException("Product count is 0");
            }
            productList.addProduct(product);
            product.setCount(product.getCount() - 1);
        }
        productListRepository.save(productList);
    }

    @Override
    public boolean deleteListProducts(Long productListId, Long userId) {
        ProductList productList = productListRepository.findById(productListId)
                .orElseThrow(() -> new RuntimeException("product lsit is emptry"));

        productList.setDeleted(true);
        productListRepository.save(productList);
        return true;
    }

    @Override
    public ResponseProductListDto findUserProductList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        List<ProductList> productLists = productListRepository.findAllByUserAndDeletedFalse(user);

        List<ProductListDto> productListDtos = new ArrayList<>();
        for (ProductList productList : productLists) {
            ProductListDto productListDto = new ProductListDto();
            productListDto.setName(productList.getName());
            productListDto.setProducts(productList.getProducts().stream()
                    .map(ProductDto::new)
                    .toList());
            productListDtos.add(productListDto);
        }

        return new ResponseProductListDto(productListDtos);
    }

}