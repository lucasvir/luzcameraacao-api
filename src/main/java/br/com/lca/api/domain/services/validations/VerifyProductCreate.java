package br.com.lca.api.domain.services.validations;

import br.com.lca.api.domain.model.dto.ProductCreateDTO;
import org.hibernate.PropertyValueException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VerifyProductCreate implements VerifyDTO<ProductCreateDTO> {


    @Override
    public void verify(ProductCreateDTO createDTO) {
        List<ValidateProperty<ProductCreateDTO>> results = new ArrayList<>();

        var name = new ValidateProperty<>(ProductCreateDTO.class, "name", createDTO.name());
        var brand = new ValidateProperty<>(ProductCreateDTO.class, "brand", createDTO.brand());
        var category = new ValidateProperty<>(ProductCreateDTO.class, "category", createDTO.category());
        var type = new ValidateProperty<>(ProductCreateDTO.class, "type", createDTO.type());
        var price = new ValidateProperty<>(ProductCreateDTO.class, "price", createDTO.price().toString());

        results.addAll(
                Arrays.asList(
                        name,
                        brand,
                        category,
                        type,
                        price
                )
        );

        for (ValidateProperty<ProductCreateDTO> properties : results) {
            if (properties.value() == null)
                throw new PropertyValueException(
                        "Illegal empty property value. Property: ",
                        properties.tClass().getName(),
                        properties.name()
                );

            if (properties.value().isBlank())
                throw new PropertyValueException(
                        "Illegal blank property value. Property: ",
                        properties.tClass().getName(),
                        properties.name()
                );
        }
    }
}
