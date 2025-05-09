package com.ucb.usuarios.service;

import com.ucb.usuarios.Command;
import com.ucb.usuarios.IUserRepository;
import com.ucb.usuarios.model.User;
import com.ucb.usuarios.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements Command<UserDto.CreateUser, String> {

    private final IUserRepository userRepository;

    public CreateUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> execute(UserDto.CreateUser userDto) {
        try {
            User user = userDto.toUser();

            if(userRepository.existsByCi(user.getCi())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El CI ya está registrado");
            }

            if(userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El email ya está en uso");
            }

            User savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario creado con ID: " + savedUser.getId());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}