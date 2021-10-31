package com.galvanize.prodman.rest;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.model.FxResponse;
import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product")
    public ProductDTO getProduct(@RequestBody IdDTO idDTO){
        return productService.getProduct(idDTO);
    }

    @PostMapping("product")
    public String createProduct(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

}
