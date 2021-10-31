package com.galvanize.prodman.rest.v1;

import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = ProductController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    public static final String BASE_URL = "/api/v1/products";
    private final ProductServiceImpl productService;

    public ProductController(final ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.getProduct(new IdDTO(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IdDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.create(productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IdDTO> deleteProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.softDelete(new IdDTO(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(new IdDTO(id), productDTO), HttpStatus.OK);
    }

}
