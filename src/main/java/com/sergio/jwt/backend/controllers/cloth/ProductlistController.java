package com.sergio.jwt.backend.controllers.cloth;

import com.sergio.jwt.backend.config.UserAuthenticationProvider;
import com.sergio.jwt.backend.dtos.cloth.ProductListDto;
import com.sergio.jwt.backend.dtos.cloth.ProductListNamesDto;
import com.sergio.jwt.backend.dtos.cloth.PurchaseDto;
import com.sergio.jwt.backend.dtos.cloth.ResponseProductListDto;
import com.sergio.jwt.backend.services.ProductListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productList")
public class ProductlistController {

    private final UserAuthenticationProvider userAuthenticationProvider;
    private final ProductListService purchaseService;

    public ProductlistController(UserAuthenticationProvider userAuthenticationProvider, ProductListService purchaseService) {
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public void purchaseProducts(@RequestBody() PurchaseDto purchaseDto, HttpServletRequest request){
        System.out.println(userAuthenticationProvider.getUserId(request));
        purchaseService.purchaseProducts(purchaseDto,userAuthenticationProvider.getUserId(request));

    }
    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable(name="id")String id, HttpServletRequest request){
        System.out.println(userAuthenticationProvider.getUserId(request));
        purchaseService.deleteListProducts(Long.valueOf(id),userAuthenticationProvider.getUserId(request));

    }
    @GetMapping()
    public ResponseProductListDto getUserProductLists(HttpServletRequest request){
        return purchaseService.findUserProductList(userAuthenticationProvider.getUserId(request));
    }
    @GetMapping("/names")
    public List<ProductListNamesDto> getUserProductListsNames(HttpServletRequest request){
        return purchaseService.findUserProductListNames(userAuthenticationProvider.getUserId(request));
    }
    @GetMapping("/{id}")
    public ProductListDto getProductList(@PathVariable(name="id") String id){
        return purchaseService.findProductList(id);
    }
}
