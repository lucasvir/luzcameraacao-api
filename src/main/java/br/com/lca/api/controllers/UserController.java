package br.com.lca.api.controllers;

import br.com.lca.api.domain.model.dto.UserCreateDTO;
import br.com.lca.api.domain.model.dto.UserDTO;
import br.com.lca.api.domain.model.dto.UserUpdateDTO;
import br.com.lca.api.services.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Add a new User",
            description = "Add a user by sending a json with the required fields",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO createDTO) {
        UserDTO user = userService.create(createDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @Operation(
            summary = "Finds a User",
            description = "Finds a User by id",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Finds all Users",
            description = "Finds all Users",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Update a existing User",
            description = "Update a existing User by sending a json with at least one field",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Updated",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO updateDTO) {
        UserDTO userUpdated = userService.update(id, updateDTO);
        return ResponseEntity.ok(userUpdated);
    }

    @Operation(
            summary = "Delete a existing User",
            description = "Delete a existing User by id",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delte(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
