package br.com.lca.api.services.impl;

import br.com.lca.api.domain.model.User;
import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.repositories.UserRepository;
import br.com.lca.api.services.validations.VerifyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VerifyDTO<UserCreateDTO> verifyDTO;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final User userMock = new User(
            "First Name",
            "Last Name",
            "email@email.com",
            "123",
            "11223445687",
            null,
            null
    );

//    private final UserDTO userDTOMock = new UserDTO(
//            1L,
//            "First Name",
//            "Last Name",
//            "email@email.com",
//            "11223445687",
//            null,
//            null,
//            null
//    );

    private final UserCreateDTO createDTOMock = new UserCreateDTO(
            "First Name",
            "Last Name",
            "email@email.com",
            "123",
            "11998899099"
    );

    //    IDEX
    @Test
    void shouldThrowNoSuchElementExceptionWhenInvalidId() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userService.findById(1L));

        String expectedMessage = "1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnUserDTOWhenValidId() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userMock));

        UserDTO result = userService.findById(1L);

        Class<? extends UserDTO> actualClass = result.getClass();
        Class<UserDTO> expectedClass = UserDTO.class;
        String expectedEmail = "email@email.com";
        String actaualEmail = result.email();

        assertEquals(expectedEmail, actaualEmail);
        assertEquals(expectedClass, actualClass);
    }

    //    CREATE
    @Test
    void shouldThrowIllegalArgumentExceptionWhenEmailAlreadyRegistered() {
        when(userRepository.existsByEmail("email@email.com")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> userService.create(createDTOMock)
        );

        String expectedMessage = "Email already registered.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnUserDTOWhenUserCreatedWithValidInputs() {
        when(userRepository.save(userMock)).thenReturn(userMock);

        UserDTO result = userService.create(createDTOMock);

        String expectedEmail = "email@email.com";
        String actualEmail = result.email();
        assertEquals(expectedEmail, actualEmail);
    }
}