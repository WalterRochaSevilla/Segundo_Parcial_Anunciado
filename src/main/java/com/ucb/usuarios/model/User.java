package com.ucb.usuarios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ci", unique = true, nullable = false)
    private Integer ci;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "active")
    private Boolean active = true;

    // Getters y setters con validaciones

    // Getters
    public Integer getId() { return id; }
    public Integer getCi() { return ci; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public Boolean getActive() { return active; }

    // Setters con validación
    public void setCi(Integer ci) {
        if (ci == null) throw new IllegalArgumentException("CI es requerido");
        if (ci < 1_000_000 || ci > 9_999_999) {
            throw new IllegalArgumentException("CI debe tener 7 dígitos");
        }
        this.ci = ci;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre es requerido");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Nombre debe tener entre 2-50 caracteres");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email es requerido");
        }
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de email inválido");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Contraseña es requerida");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Contraseña debe tener mínimo 8 caracteres");
        }
        this.password = password;
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Formato de teléfono inválido");
        }
        this.phone = phone;
    }

    public void setActive(Boolean active) {
        this.active = active != null ? active : true;
    }

    public static class UpdateUserDto {
        private final Integer id;
        private UserDto.UpdateUser updateData;

        public UpdateUserDto(Integer id, UserDto.UpdateUser updateData) {
            this.id = id;
            this.updateData = updateData;
        }

        // Getters
        public Integer getId() { return id; }
        public UserDto.UpdateUser getUpdateData() { return updateData; }

        // Setters
        public void setUpdateData(UserDto.UpdateUser updateData) {
            this.updateData = updateData;
        }
    }
}