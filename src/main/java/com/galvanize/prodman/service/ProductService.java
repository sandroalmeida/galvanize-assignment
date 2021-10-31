package com.galvanize.prodman.service;

import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;

import java.util.List;

public interface ProductService {

    IdDTO create(final ProductDTO productDTO);
    ProductDTO getProduct(IdDTO idDTO);
    IdDTO softDelete(IdDTO idDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(IdDTO idDTO, ProductDTO productDTO);

}
