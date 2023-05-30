package com.sergio.jwt.backend.controllers.cloth;

import com.sergio.jwt.backend.dtos.cloth.ProductDto;
import com.sergio.jwt.backend.services.FillUpService;
import com.sergio.jwt.backend.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final FillUpService fillUpService;

    public ProductController(ProductService productService, FillUpService fillUpService) {
        this.productService = productService;
        this.fillUpService = fillUpService;
    }

    @PostMapping()
    public String saveProduct(@RequestBody() ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @GetMapping("/category/{categoryName}")
    public List<ProductDto> findAllProducts(@PathVariable String categoryName) {
        return productService.getAllProducts(categoryName);
    }
    @GetMapping("/fill")
    public void fillDataBase(){
        fillUpService.fillUpDataBase();
    }
}
