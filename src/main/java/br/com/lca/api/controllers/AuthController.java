package br.com.lca.api.controllers;

import br.com.lca.api.domain.model.dto.*;
import br.com.lca.api.services.AuthService;
import br.com.lca.api.services.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Add a new User",
            description = "Add a new User by sending a json with the required fields",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping("/signup")
    ResponseEntity<UserDTO> signUp(@RequestBody UserCreateDTO createDTO) {
        UserDTO user = userService.create(createDTO);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Authenticates a User",
            description = "Authenticates a User by sending a json with the required fields",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserLoginDTO.class))
                    ),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping("/login")
    ResponseEntity<AuthLoginResponseDTO> login(@RequestBody UserLoginDTO loginDTO) {
        AuthLoginResponseDTO login = authService.login(loginDTO);
        return ResponseEntity.ok(login);
    }
}
