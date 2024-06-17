package br.com.lca.api.controllers;

import br.com.lca.api.domain.model.dto.AuthLoginResponseDTO;
import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserLoginDTO;
import br.com.lca.api.domain.services.AuthService;
import br.com.lca.api.domain.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signUp(@RequestBody UserCreateDTO createDTO) {
        UserDTO user = userService.create(createDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    ResponseEntity<AuthLoginResponseDTO> login(@RequestBody UserLoginDTO loginDTO) {
        AuthLoginResponseDTO login = authService.login(loginDTO);
        return ResponseEntity.ok(login);
    }
}
