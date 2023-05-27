package com.sergio.jwt.backend.controllers.tobacoo;

import com.sergio.jwt.backend.dtos.tobacoo.ProductDto;
import com.sergio.jwt.backend.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public String saveProduct(@RequestBody() ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @GetMapping("/category/{id}")
    public List<ProductDto> findAllProducts(@PathVariable String id) {
        return productService.getAllProducts(id);
    }
}
