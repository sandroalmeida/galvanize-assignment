package com.galvanize.prodman.rest.v1;

import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = ProductController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    public static final String BASE_URL = "/api/v1/products";
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProduct(@PathVariable String id){
        return productService.getProduct(new IdDTO(id));
    }

    @GetMapping("/{id}/{currency}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProduct(@PathVariable String id, @PathVariable String currency){
        return productService.getProduct(new IdDTO(id), currency);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IdDTO deleteProduct(@PathVariable String id){
        return productService.softDelete(new IdDTO(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(new IdDTO(id), productDTO);
    }

}
