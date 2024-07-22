package br.com.lca.api.services.impl;

import br.com.lca.api.domain.model.Product;
import br.com.lca.api.domain.model.dto.ProductCreateDTO;
import br.com.lca.api.domain.model.dto.ProductDTO;
import br.com.lca.api.domain.model.dto.ProductUpdateDTO;
import br.com.lca.api.domain.model.enums.Category;
import br.com.lca.api.domain.model.enums.Type;
import br.com.lca.api.domain.repositories.ProductRepository;
import br.com.lca.api.services.validations.VerifyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private VerifyDTO<ProductCreateDTO> verifyDTO;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Product productEntityMock = new Product(
            "Product Name",
            "Product Brand",
            Category.CAMERA,
            Type.FLASH,
            BigDecimal.valueOf(10.00)
    );

    private ProductDTO productDTOMock = new ProductDTO(
            1L,
            "Product Name",
            "Product Brand",
            Category.CAMERA,
            Type.FLASH,
            BigDecimal.valueOf(10.00),
            true
    );

    private ProductCreateDTO productCreateDTOMock = new ProductCreateDTO(
            null,
            "Product Name",
            "Product Brand",
            "CM",
            "FL",
            BigDecimal.valueOf(10.00)
    );

    private ProductUpdateDTO productUpdateDTOMock = new ProductUpdateDTO(
            null,
            "Updated Product Name",
            null,
            null,
            null,
            null
    );

    //FIND BY ID
    @Test
    void shouldThrowNoSuchElementExceptionWhenInvalidId() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException response = assertThrows(NoSuchElementException.class, () -> productService.findById(1L));

        String expectedMessage = "1";
        String actualMessage = response.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnProductDTOWhenValidId() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntityMock));

        ProductDTO response = productService.findById(1L);

        Class<? extends ProductDTO> actualClass = response.getClass();
        Class<ProductDTO> expectedClass = ProductDTO.class;
        assertEquals(expectedClass, actualClass);

        String expectedName = "Product Name";
        String actualName = response.name();
        assertEquals(expectedName, actualName);

        boolean expectedIsAvailable = true;
        boolean actualIsAvailable = response.isAvailable();
        assertEquals(expectedIsAvailable, actualIsAvailable);
    }

    //CREATE
    @Test
    void shouldReturnProductDTOWhenProductCreatedWithValidInputs() {
        when(productRepository.save(productEntityMock)).thenReturn(productEntityMock);

        ProductDTO response = productService.create(productCreateDTOMock);

        Class<? extends ProductDTO> actualClass = response.getClass();
        Class<ProductDTO> expectedClass = ProductDTO.class;
        assertEquals(expectedClass, actualClass);

        String expectedName = "Product Name";
        String actualName = response.name();
        assertEquals(expectedName, actualName);

        boolean expectedIsAvailable = true;
        boolean actualIsAvailable = response.isAvailable();
        assertEquals(expectedIsAvailable, actualIsAvailable);
    }

    //UPDATE
    @Test
    void shouldThrowNoSuchElementExceptionWhenUpadatingWithInvalidId() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException response
                = assertThrows(NoSuchElementException.class, () -> productService.update(1L, productUpdateDTOMock));

        String expectedMessage = "1";
        String actualMessage = response.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    //DELETE
    @Test
    void shouldThrowNoSuchElementExceptionWhenDeletingWithInvalidId() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException response
                = assertThrows(NoSuchElementException.class, () -> productService.delete(1L));

        String expectedMessage = "1";
        String actualMessage = response.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}