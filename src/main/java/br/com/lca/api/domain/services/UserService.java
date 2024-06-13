package br.com.lca.api.domain.services;

import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO create(UserCreateDTO createDTO);

    List<UserDTO> findAll();

    UserDTO update(Long id, UserUpdateDTO updateDTO);

    void delete(Long id);
}
