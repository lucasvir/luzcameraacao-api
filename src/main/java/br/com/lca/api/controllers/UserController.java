package br.com.lca.api.controllers;

import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserUpdateDTO;
import br.com.lca.api.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO createDTO) {
        UserDTO user = userService.create(createDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO updateDTO) {
        UserDTO userUpdated = userService.update(id, updateDTO);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delte(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
