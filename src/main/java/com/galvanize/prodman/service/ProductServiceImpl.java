package com.galvanize.prodman.service;

import com.galvanize.prodman.domain.Product;
import com.galvanize.prodman.mapper.ProductMapper;
import com.galvanize.prodman.model.FxResponse;
import com.galvanize.prodman.model.IdDTO;
import com.galvanize.prodman.model.ProductDTO;
import com.galvanize.prodman.repository.ProductRepository;
import com.galvanize.prodman.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final FxService fxService;

    public ProductServiceImpl(final ProductRepository productRepository, final FxService fxService) {
        this.productRepository = productRepository;
        this.fxService = fxService;
    }

    @Override
    public IdDTO create(final ProductDTO productDTO) {
        Product product = productMapper.productDTOtoProduct(productDTO);
        product.setDeleted(false);
        product.setStrId(RandomString.getAlphaNumericString(50));
        return new IdDTO(productRepository.save(product).getStrId());
    }

    @Override
    public ProductDTO getProduct(IdDTO idDTO){
        Product product = productRepository.getByStrId(idDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);
        product.setViews(product.getViews() + 1);
        productRepository.save(product);

        return productMapper.productToProductDTO(product);
    }

    @Override
    public ProductDTO getProduct(IdDTO idDTO, String currency){
        Product product = productRepository.getByStrId(idDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);
        product.setViews(product.getViews() + 1);
        productRepository.save(product);

        FxResponse response = fxService.getQuotes();
        if(!response.getQuotes().isEmpty()){
            for(Map.Entry<String, Double> quote: response.getQuotes().entrySet()){
                if(quote.getKey().contains(currency)){
                    product.setPrice(product.getPrice() * quote.getValue());
                    break;
                }
            }
        }
        return productMapper.productToProductDTO(product);
    }

    @Override
    public IdDTO softDelete(IdDTO idDTO){
        Product product = productRepository.getByStrId(idDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);
        product.setDeleted(true);
        productRepository.save(product);
        return idDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(IdDTO idDTO, ProductDTO productDTO){
        Product product = productRepository.getByStrId(idDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);
        if(product.getDeleted())
            throw new ResourceForbiddenException();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return productMapper.productToProductDTO(product);
    }

}
