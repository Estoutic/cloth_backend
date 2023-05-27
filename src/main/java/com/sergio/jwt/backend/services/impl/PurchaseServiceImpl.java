package com.sergio.jwt.backend.services.impl;

import com.sergio.jwt.backend.dtos.tobacoo.ProductDto;
import com.sergio.jwt.backend.dtos.tobacoo.PurchaseDto;
import com.sergio.jwt.backend.entites.Order;
import com.sergio.jwt.backend.entites.tobacoo.Product;
import com.sergio.jwt.backend.repositories.OrderRepository;
import com.sergio.jwt.backend.repositories.ProductRepository;
import com.sergio.jwt.backend.services.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public PurchaseServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void purchaseProducts(PurchaseDto purchaseDto, Long userId) {
        List<Order.OrderItem> items = purchaseDto.getProducts().stream()
                .map(p -> Order.OrderItem.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .imageLink(p.getImageLink())
                        .price(p.getPrice())
                        .count(p.getCount())
                        .build())
                .collect(Collectors.toList());

        int totalPrice = purchaseDto.getProducts().stream()
                .mapToInt(p -> p.getPrice() * p.getCount())
                .sum();

        List<Product> updatedProducts = new ArrayList<>();

        for (ProductDto productDto : purchaseDto.getProducts()) {
            Product product = productRepository.findById(productDto.getId())
                    .orElseThrow(() -> new RuntimeException("Product id " +  productDto.getId()));

            if (product.getCount() < productDto.getCount()) {
                throw new RuntimeException("Not enough products for purchase: " + product.getName());
            }

            product.setCount(product.getCount() - productDto.getCount());
            updatedProducts.add(product);
        }

        Order order = Order.builder()
                .userId(userId)
                .items(items)
                .totalPrice(totalPrice)
                .build();

        orderRepository.save(order);
        updatedProducts.forEach(p -> productRepository.save(p));

    }
}