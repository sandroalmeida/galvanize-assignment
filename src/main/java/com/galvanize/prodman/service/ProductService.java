package com.galvanize.prodman.service;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.mapper.ProductMapper;
import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.repository.ProductRepository;
import com.galvanize.prodman.util.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final FxService fxService;

    public ProductService(final ProductRepository productRepository, final FxService fxService) {
        this.productRepository = productRepository;
        this.fxService = fxService;
    }

    public String create(final ProductDTO productDTO) {
        Product product = productMapper.productDTOtoProduct(productDTO);
        product.setDeleted(false);
        product.setViews(0);
        product.setStrId(RandomString.getAlphaNumericString(50));
        return productRepository.save(product).getStrId();
    }

    public void delete(final Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDTO getProduct(IdDTO idDTO){
        Product product = productRepository.getByStrId(idDTO.getId());
        return productMapper.productToProductDTO(product);
    }

//    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
//        product.setName(productDTO.getName());
//        product.setDescription(productDTO.getDescription());
//        product.setPrice(productDTO.getPrice());
//        product.setStrId(RandomString.getAlphaNumericString(50));
//        product.setViews(0);
//        product.setDeleted(false);
//        return product;
//    }
//
//    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
//        productDTO.setName(product.getName());
//        productDTO.setDescription(product.getDescription());
//        productDTO.setPrice(product.getPrice());
//        productDTO.setStrId(product.getStrId());
//        return productDTO;
//    }

}
