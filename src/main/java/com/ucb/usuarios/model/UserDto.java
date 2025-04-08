package com.ucb.usuarios.model;

public class UserDto {
    private Integer ci;
    private String name;
    private String email;
    private String phone;

    // Constructor para entidad User
    public UserDto(User user) {
        this.ci = user.getCi();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    // Constructor para errores
    public UserDto(String error) {
        this.ci = null;
        this.name = "Error";
        this.email = "Error";
        this.phone = error;
    }

    // Getters y Setters
    public Integer getCi() { return ci; }
    public void setCi(Integer ci) { this.ci = ci; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // DTO para creación de usuario
    public static class CreateUser {
        private Integer ci;
        private String name;
        private String email;
        private String password;
        private String phone;

        // Validación al convertir a entidad
        public User toUser() {
            User user = new User();

            // Validar CI
            if (ci == null) throw new IllegalArgumentException("CI es requerido");
            if (ci < 1_000_000 || ci > 9_999_999) {
                throw new IllegalArgumentException("CI debe tener 7 dígitos");
            }
            user.setCi(ci);

            // Validar nombre
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Nombre es requerido");
            }
            if (name.length() < 2 || name.length() > 50) {
                throw new IllegalArgumentException("Nombre debe tener 2-50 caracteres");
            }
            user.setName(name);

            // Validar email
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email es requerido");
            }
            if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new IllegalArgumentException("Formato de email inválido");
            }
            user.setEmail(email);

            // Validar contraseña
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Contraseña es requerida");
            }
            if (password.length() < 8) {
                throw new IllegalArgumentException("Contraseña debe tener mínimo 8 caracteres");
            }
            user.setPassword(password);

            // Validar teléfono
            if (phone != null && !phone.matches("^\\+?[0-9]{7,15}$")) {
                throw new IllegalArgumentException("Formato de teléfono inválido");
            }
            user.setPhone(phone);

            return user;
        }

        // Getters y Setters
        public Integer getCi() { return ci; }
        public void setCi(Integer ci) { this.ci = ci; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }

    // DTO para actualización
    public static class UpdateUser {
        private String name;
        private String email;
        private String phone;

        public void applyUpdate(User user) {
            if (name != null) {
                if (name.length() < 2 || name.length() > 50) {
                    throw new IllegalArgumentException("Nombre debe tener 2-50 caracteres");
                }
                user.setName(name);
            }

            if (email != null) {
                if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    throw new IllegalArgumentException("Formato de email inválido");
                }
                user.setEmail(email);
            }

            if (phone != null) {
                if (!phone.matches("^\\+?[0-9]{7,15}$")) {
                    throw new IllegalArgumentException("Formato de teléfono inválido");
                }
                user.setPhone(phone);
            }
        }

        // Getters y Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }
}