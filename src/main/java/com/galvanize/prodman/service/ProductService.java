package com.galvanize.prodman.service;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.repository.ProductRepository;
import com.galvanize.prodman.util.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final FxService fxService;

    public ProductService(final ProductRepository productRepository, final FxService fxService) {
        this.productRepository = productRepository;
        this.fxService = fxService;
    }

    public String create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getStrId();
    }

    public void delete(final Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDTO getProduct(IdDTO idDTO){
        final ProductDTO productDTO = new ProductDTO();
        Product product = productRepository.getByStrId(idDTO.getId());
        return mapToDTO(product, productDTO);
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStrId(RandomString.getAlphaNumericString(50));
        product.setViews(0);
        product.setDeleted(false);
        return product;
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStrId(product.getStrId());
        return productDTO;
    }

}
