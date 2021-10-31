package com.galvanize.prodman.mapper;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);
    Product productDTOtoProduct(ProductDTO productDTO);
}
