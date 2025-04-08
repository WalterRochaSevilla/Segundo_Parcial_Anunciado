package com.ucb.usuarios.service;

import com.ucb.usuarios.IUserRepository;
import com.ucb.usuarios.Query;
import com.ucb.usuarios.model.User;
import com.ucb.usuarios.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserService implements Query<Integer, UserDto> {

    private final IUserRepository userRepository;

    public GetUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto execute(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> new UserDto(u)).orElse(null);
    }
}