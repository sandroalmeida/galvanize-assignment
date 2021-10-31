package com.galvanize.prodman.rest.v1;

import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/products/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductDTO getProduct(@PathVariable String id){
        return productService.getProduct(new IdDTO(id));
    }

    @PostMapping
    public String createProduct(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable String id){
        return productService.softDelete(new IdDTO(id));
    }



}
