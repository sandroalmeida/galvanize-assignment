package com.galvanize.prodman.rest;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.model.FxResponse;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product")
    public String getProduct(@RequestBody ProductDTO productDTO){

        Product product = productService.getProduct(productDTO);
        return product.getDescription();

    }

}
