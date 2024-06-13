package br.com.lca.api.domain.services.impl;

import br.com.lca.api.domain.model.User;
import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserUpdateDTO;
import br.com.lca.api.domain.repositories.UserRepository;
import br.com.lca.api.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return new UserDTO(user);
    }

    @Override
    public UserDTO create(UserCreateDTO createDTO) {
        boolean emailExists = createDTO.email() != null && userRepository.existsByEmail(createDTO.email());
        if(emailExists) throw new IllegalArgumentException("Email already registered.");

        User user = new User(createDTO);
        User userEntity = userRepository.save(user);

        return new UserDTO(userEntity);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) throw new NoSuchElementException("Empty resource.");

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
}