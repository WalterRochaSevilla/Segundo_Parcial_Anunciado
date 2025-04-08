package com.ucb.usuarios.service;

import com.ucb.usuarios.IUserRepository;
import com.ucb.usuarios.Query;
import com.ucb.usuarios.model.User;
import com.ucb.usuarios.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserService implements Query<Integer, UserDto> {

    private final IUserRepository userRepository;

    public GetUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDto> execute(Integer id) {
        try {
            Optional<User> user = userRepository.findById(id);

            return user.map(u -> ResponseEntity.ok(new UserDto(u)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UserDto(e.getMessage()));
        }
    }
}