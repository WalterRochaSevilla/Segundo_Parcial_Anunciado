package com.ucb.usuarios.service;

import com.ucb.usuarios.Command;
import com.ucb.usuarios.IUserRepository;
import com.ucb.usuarios.model.UpdateUserDto;
import com.ucb.usuarios.model.User;
import com.ucb.usuarios.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserService implements Command<UpdateUserDto, UserDto> {

    private final IUserRepository userRepository;

    public UpdateUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDto> execute(UpdateUserDto input) {
        try {
            Optional<User> userOpt = userRepository.findById(input.getId());

            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            User user = userOpt.get();
            UserDto.UpdateUser updateData = input.getUpdateData();

            // Aplicar actualizaciones
            if (updateData.getName() != null) user.setName(updateData.getName());
            if (updateData.getEmail() != null) user.setEmail(updateData.getEmail());
            if (updateData.getPhone() != null) user.setPhone(updateData.getPhone());

            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(new UserDto(updatedUser));

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UserDto(e.getMessage()));
        }
    }
}