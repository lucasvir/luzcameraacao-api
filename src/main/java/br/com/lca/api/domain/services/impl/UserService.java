package br.com.lca.api.domain.services.impl;

import br.com.lca.api.controllers.exceptions.EmptyResourceException;
import br.com.lca.api.domain.model.User;
import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserUpdateDTO;
import br.com.lca.api.domain.repositories.UserRepository;
import br.com.lca.api.domain.services.ServiceStrategy;
import br.com.lca.api.domain.services.validations.VerifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements ServiceStrategy<UserDTO, UserCreateDTO, UserUpdateDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerifyDTO<UserCreateDTO> verifyCreateDTO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));

        return new UserDTO(user);
    }

    @Override
    public UserDTO create(UserCreateDTO createDTO) {
        boolean emailExists = createDTO.email() != null && userRepository.existsByEmail(createDTO.email());
        if (emailExists) throw new IllegalArgumentException("Email already registered.");

        verifyCreateDTO.verify(createDTO);

        User user = new User(
                createDTO.firstName(),
                createDTO.lastName(),
                createDTO.email(),
                passwordEncoder.encode(createDTO.password()),
                createDTO.telephone(),
                null,
                null
        );

        User userEntity = userRepository.save(user);

        return new UserDTO(userEntity);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new EmptyResourceException();

        return users.stream().map(UserDTO::new).toList();
    }

    @Override
    public UserDTO update(Long id, UserUpdateDTO updateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        user.updateData(updateDTO);
        User updatedUser = userRepository.save(user);

        return new UserDTO(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(email));
    }
}
