package com.sergio.jwt.backend.services.impl;

import com.sergio.jwt.backend.entites.cloth.Category;
import com.sergio.jwt.backend.entites.cloth.Product;
import com.sergio.jwt.backend.repositories.CategoryRepository;
import com.sergio.jwt.backend.repositories.ProductRepository;
import com.sergio.jwt.backend.services.FillUpService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillUpServiceImpl implements FillUpService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public FillUpServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void fillUpDataBase() {

        Category jeans = new Category("jeans", "Джинсы");
        Category jackets = new Category("jackets", "Куртки");
        Category coat = new Category("coat", "Пальто");
        Category skirts = new Category("skirts", "Юбки");

        /*ДЖИНСЫ*/

        Product highRiseJeans = new Product(
                "ДЖИНСЫ HIGH RISE",
                "https://zara-russia.store/image/cache/catalog/files/6688212400_15_1_1-600x900.jpg",
                2100,
                10,
                jeans
        );

        Product splitJeans = new Product(
                "ДЖИНСЫ SPLIT",
                "https://zara-russia.store/image/cache/catalog/files/0774809802_1_1_1-600x900.jpg",
                1100,
                100,
                jeans
        );

        Product fittedJeans = new Product(
                "БАЗОВЫЕ ДЖИНСЫ ОБЛЕГАЮЩЕГО КРОЯ",
                "https://zara-russia.store/image/cache/catalog/files/5575440407_1_1_1-600x900.jpg",
                2000,
                69,
                jeans);

        Product croppedJeans = new Product(
                "ДЖИНСЫ CROPPED X RHUIGI",
                "https://zara-russia.store/image/cache/catalog/files/1538326250_1_1_1-600x900.jpg",
                3400,
                7,
                jeans
        );

        /*КУРТКИ*/

        Product oversizedDenimJacket = new Product(
                "ДЖИНСОВАЯ КУРТКА-РУБАШКА В СТИЛЕ OVERSIZE",
                "https://zara-russia.store/image/cache/catalog/files/0108034250_1_1_1-600x900.jpg",
                990,
                20,
                jackets
        );

        Product basicDenimJacket = new Product(
                "БАЗОВАЯ ДЖИНСОВАЯ КУРТКА",
                "https://zara-russia.store/image/cache/catalog/files/1620300406_1_1_1-600x900.jpg",
                780,
                15,
                jackets
        );

        Product quiltedJacket = new Product(
                "БАЗОВАЯ СТЕГАНАЯ КУРТКА",
                "https://zara-russia.store/image/cache/catalog/files/8281306800_1_1_1-600x900.jpg",
                1100,
                10,
                jackets
        );

        /*ПАЛЬТО*/

        Product fauxShearlingCoat = new Product(
                "ДВУБОРТНОЕ ПАЛЬТО ИЗ ИСКУССТВЕННОЙ ОВЧИНЫ",
                "https://zara-russia.store/image/cache/catalog/files/4360045802_1_1_1-600x900.jpg",
                1200,
                100,
                coat
        );

        Product woolBlendCoat = new Product(
                "ДВУБОРТНОЕ ПАЛЬТО ИЗ СМЕСОВОЙ ШЕРСТИ",
                "https://zara-russia.store/image/cache/catalog/files/9040744800_1_1_1-600x900.jpg",
                700,
                50,
                coat
        );

        Product checkeredCoat = new Product(
                "ДВУБОРТНОЕ ПАЛЬТО В КЛЕТКУ",
                "https://zara-russia.store/image/cache/catalog/files/4154222707_1_1_1-600x900.jpg",
                850,
                60,
                coat
        );

        /*ЮБКИ*/

        Product satinMidiSkirt = new Product(
                "АТЛАСНАЯ ЮБКА МИДИ",
                "https://zara-russia.store/image/cache/catalog/files/4661206800_1_1_1-600x900.jpg",
                900,
                50,
                skirts
        );

        categoryRepository.saveAll(List.of(jeans, jackets, coat, skirts));
        productRepository.saveAll(List.of(highRiseJeans, splitJeans, fittedJeans, croppedJeans,
                oversizedDenimJacket, basicDenimJacket, quiltedJacket, fauxShearlingCoat, woolBlendCoat, checkeredCoat, satinMidiSkirt));

    }
}
