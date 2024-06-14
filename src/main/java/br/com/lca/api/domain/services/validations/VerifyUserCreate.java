package br.com.lca.api.domain.services.validations;

import org.hibernate.PropertyValueException;
import org.springframework.stereotype.Component;

import br.com.lca.api.domain.model.dto.UserCreateDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VerifyUserCreate implements VerifyDTO<UserCreateDTO> {

    @Override
    public void verify(UserCreateDTO createDTO) {
        List<ValidateProperty<UserCreateDTO>> results = new ArrayList<>();

        var firstName = new ValidateProperty<>(UserCreateDTO.class, "firstName", createDTO.firstName());
        var lastName = new ValidateProperty<>(UserCreateDTO.class, "lastName", createDTO.lastName());
        var email = new ValidateProperty<>(UserCreateDTO.class, "email", createDTO.email());
        var password = new ValidateProperty<>(UserCreateDTO.class, "password", createDTO.password());
        var telephone = new ValidateProperty<>(UserCreateDTO.class, "telephone", createDTO.telephone());

        results.addAll(
                Arrays.asList(
                        firstName,
                        lastName,
                        email,
                        password,
                        telephone
                )
        );

        for (ValidateProperty<UserCreateDTO> properties : results) {
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
