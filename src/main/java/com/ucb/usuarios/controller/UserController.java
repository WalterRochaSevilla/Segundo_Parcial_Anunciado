package com.ucb.usuarios.controller;

import com.ucb.usuarios.model.UpdateUserDto;
import com.ucb.usuarios.model.UserDto;
import com.ucb.usuarios.service.AllUserService;
import com.ucb.usuarios.service.CreateUserService;
import com.ucb.usuarios.service.UpdateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "API para gestión de usuarios")
public class UserController {

    private final AllUserService allUserService;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    public UserController(AllUserService allUserService,
                          CreateUserService createUserService,
                          UpdateUserService updateUserService) {
        this.allUserService = allUserService;
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
    }

    @PostMapping
    @Operation(
            summary = "Crear usuario",
            description = "Registra un nuevo usuario en el sistema con CI, nombre, email y contraseña.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario creado exitosamente",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos o duplicados",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )
    public ResponseEntity<String> create(@RequestBody UserDto.CreateUser userDto) {
        return createUserService.execute(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return allUserService.execute(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        // Lógica de eliminación
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDto.UpdateUser updateData) {
        return this.updateUserService.execute(new UpdateUserDto(id, updateData));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}