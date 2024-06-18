package br.com.lca.api.services;

import br.com.lca.api.config.security.JWTService;
import br.com.lca.api.domain.model.User;
import br.com.lca.api.domain.model.dto.AuthLoginResponseDTO;
import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserLoginDTO;
import br.com.lca.api.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    public UserDTO signUp(UserCreateDTO createDTO) {
        return userService.create(createDTO);
    }

    public AuthLoginResponseDTO login(UserLoginDTO loginDTO) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.password()
                )
        );

        User user = userService.findByEmail(loginDTO.email());
        String token = jwtService.generateToken(user);

        return new AuthLoginResponseDTO(token, jwtService.getExpirationTime());
    }
}
