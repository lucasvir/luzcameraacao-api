package br.com.lca.api.services.validations;

import br.com.lca.api.domain.model.dto.OrderCreateDTO;
import org.hibernate.PropertyValueException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VerifyOrderCreate implements VerifyDTO<OrderCreateDTO> {

    @Override
    public void verify(OrderCreateDTO createDTO) {
        List<ValidateProperty<OrderCreateDTO>> properties = new ArrayList<>();

        var userId = new ValidateProperty<>(OrderCreateDTO.class, "userId", createDTO.userId().toString());
        var location = new ValidateProperty<>(OrderCreateDTO.class, "location", createDTO.location());
        var complement = new ValidateProperty<>(OrderCreateDTO.class, "complement", createDTO.complement());
        var city = new ValidateProperty<>(OrderCreateDTO.class, "city", createDTO.city());
        var uf = new ValidateProperty<>(OrderCreateDTO.class, "uf", createDTO.uf());
        var startedAt = new ValidateProperty<>(OrderCreateDTO.class, "startedAt", createDTO.startedAt().toString());
        var expiresAt = new ValidateProperty<>(OrderCreateDTO.class, "expiresAt", createDTO.expiresAt().toString());
        var productsId = new ValidateProperty<>(OrderCreateDTO.class, "productsId", createDTO.productsId().toString());
        properties.addAll(
                Arrays.asList(
                        userId,
                        location,
                        complement,
                        city,
                        uf,
                        startedAt,
                        expiresAt,
                        productsId
                )
        );

        for (ValidateProperty<OrderCreateDTO> property : properties) {
            if (property.value() == null)
                throw new PropertyValueException(
                        "Illegal empty property value. Property: ",
                        property.tClass().getName(),
                        property.name()
                );

            if (property.value().isBlank())
                throw new PropertyValueException(
                        "Illegal blank property value. Property: ",
                        property.tClass().getName(),
                        property.name()
                );
        }
    }
}
