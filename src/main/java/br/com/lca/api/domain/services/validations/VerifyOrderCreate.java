package br.com.lca.api.domain.services.validations;

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
        List<ValidateProperty<OrderCreateDTO>> results = new ArrayList<>();

        var userId = new ValidateProperty<>(OrderCreateDTO.class, "userId", createDTO.userId().toString());
        var location = new ValidateProperty<>(OrderCreateDTO.class, "location", createDTO.location());
        var complement = new ValidateProperty<>(OrderCreateDTO.class, "complement", createDTO.complement());
        var uf = new ValidateProperty<>(OrderCreateDTO.class, "uf", createDTO.uf());
        var startedAt = new ValidateProperty<>(OrderCreateDTO.class, "startedAt", createDTO.startedAt().toString());
        var expiresAt = new ValidateProperty<>(OrderCreateDTO.class, "expiresAt", createDTO.expiresAt().toString());
        var totalValue = new ValidateProperty<>(OrderCreateDTO.class, "totalValue", createDTO.totalValue().toString());

        results.addAll(
                Arrays.asList(
                        userId,
                        location,
                        complement,
                        uf,
                        startedAt,
                        expiresAt,
                        totalValue
                )
        );

        for (ValidateProperty<OrderCreateDTO> properties : results) {
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
