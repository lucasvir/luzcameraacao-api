package br.com.lca.api.controllers;

import br.com.lca.api.domain.model.dto.ProductCreateDTO;
import br.com.lca.api.domain.model.dto.ProductDTO;
import br.com.lca.api.domain.model.dto.ProductUpdateDTO;
import br.com.lca.api.domain.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @PostMapping
    ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO createDTO) {
        ProductDTO product = productService.create(createDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.id()).toUri();

        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> show(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    ResponseEntity<List<ProductDTO>> index() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductUpdateDTO updateDTO) {
        ProductDTO product = productService.update(id, updateDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
