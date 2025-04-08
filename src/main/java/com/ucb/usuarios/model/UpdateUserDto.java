package com.ucb.usuarios.model;

public class UpdateUserDto {
    private final Integer id;
    private UserDto.UpdateUser updateData;

    public UpdateUserDto(Integer id, UserDto.UpdateUser updateData) {
        this.id = id;
        this.updateData = updateData;
    }

    // Getters
    public Integer getId() { return id; }
    public UserDto.UpdateUser getUpdateData() { return updateData; }
}