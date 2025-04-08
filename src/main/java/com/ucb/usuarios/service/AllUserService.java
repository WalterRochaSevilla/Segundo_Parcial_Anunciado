package com.ucb.usuarios.service;

import com.ucb.usuarios.IUserRepository;
import com.ucb.usuarios.Query;
import com.ucb.usuarios.model.User;
import com.ucb.usuarios.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllUserService implements Query<Void, List<UserDto>> {

    private final IUserRepository userRepository;

    public AllUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<UserDto>> execute(Void input) {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }
}