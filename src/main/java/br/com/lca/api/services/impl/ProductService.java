package br.com.lca.api.services.impl;

import br.com.lca.api.controllers.exceptions.EmptyResourceException;
import br.com.lca.api.domain.model.Product;
import br.com.lca.api.domain.model.dto.ProductCreateDTO;
import br.com.lca.api.domain.model.dto.ProductDTO;
import br.com.lca.api.domain.model.dto.ProductUpdateDTO;
import br.com.lca.api.domain.model.enums.Category;
import br.com.lca.api.domain.model.enums.Type;
import br.com.lca.api.domain.repositories.ProductRepository;
import br.com.lca.api.services.ServiceStrategy;
import br.com.lca.api.services.validations.VerifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements ServiceStrategy<ProductDTO, ProductCreateDTO, ProductUpdateDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VerifyDTO<ProductCreateDTO> verifyDTO;

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));

        return new ProductDTO(product);
    }

    @Override
    public ProductDTO create(ProductCreateDTO createDTO) {
        verifyDTO.verify(createDTO);

        Product product = new Product(
                createDTO.name(),
                createDTO.brand(),
                Category.fromCode(createDTO.category()),
                Type.fromCode(createDTO.type()),
                createDTO.price()
        );

        Product productEntity = productRepository.save(product);
        return new ProductDTO(productEntity);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new EmptyResourceException();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Override
    public ProductDTO update(Long id, ProductUpdateDTO updateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));

        product.updateData(updateDTO);
        Product productUpdated = productRepository.save(product);

        return new ProductDTO(productUpdated);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));
        productRepository.delete(product);
    }
}
