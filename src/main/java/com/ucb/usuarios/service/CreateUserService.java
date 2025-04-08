package com.ucb.usuarios.service;

import com.ucb.usuarios.Command;
import com.ucb.usuarios.IUserRepository;
import com.ucb.usuarios.model.User;
import com.ucb.usuarios.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements Command<UserDto.CreateUser, String> {

    private final IUserRepository userRepository;

    public CreateUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(UserDto.CreateUser userDto) {
        User user = userDto.toUser();
        if(userRepository.existsByCi(user.getCi())) {
            throw new IllegalArgumentException("El CI ya está registrado");
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        User savedUser = userRepository.save(user);
        return "Usuario creado con ID: " + savedUser.getId();
    }
}