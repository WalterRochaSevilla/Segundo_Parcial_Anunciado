package com.ucb.usuarios.controller;

import com.ucb.usuarios.model.UpdateUserDto;
import com.ucb.usuarios.model.UserDto;
import com.ucb.usuarios.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
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
    public ResponseEntity<?> createUser(@RequestBody UserDto.CreateUser userDto) {
        try {
            String result = createUserService.execute(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return allUserService.execute(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDto.UpdateUser updateData) {
        return updateUserService.execute(new UpdateUserDto(id, updateData));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationExceptions(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}