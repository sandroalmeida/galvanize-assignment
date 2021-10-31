package com.galvanize.prodman.service;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.mapper.ProductMapper;
import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.repository.ProductRepository;
import com.galvanize.prodman.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final FxService fxService;

    public ProductService(final ProductRepository productRepository, final FxService fxService) {
        this.productRepository = productRepository;
        this.fxService = fxService;
    }

    public IdDTO create(final ProductDTO productDTO) {
        Product product = productMapper.productDTOtoProduct(productDTO);
        product.setDeleted(false);
        product.setViews(0);
        product.setStrId(RandomString.getAlphaNumericString(50));
        return new IdDTO(productRepository.save(product).getStrId());
    }

    public void delete(final Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDTO getProduct(IdDTO idDTO){
        Product product = productRepository.getByStrId(idDTO.getId());
        return productMapper.productToProductDTO(product);
    }

    public IdDTO softDelete(IdDTO idDTO){
        Product product = productRepository.getByStrId(idDTO.getId());
        product.setDeleted(true);
        productRepository.save(product);
        return idDTO;
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(IdDTO idDTO, ProductDTO productDTO){
        Product product = productRepository.getByStrId(idDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return productMapper.productToProductDTO(product);
    }

}
