package com.ucb.usuarios.model;

import java.util.regex.Pattern;

public class UserDto {
    private Integer ci;
    private String name;
    private String email;
    private String phone;

    public UserDto(User user) {
        this.ci = user.getCi();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public UserDto(String error) {
        this.ci = null;
        this.name = "Error";
        this.email = "Error";
        this.phone = error;
    }

    // Getters y Setters
    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static class CreateUser {
        private Integer ci;
        private String name;
        private String email;
        private String password;
        private String phone;

        public User toUser() {
            User user = new User();
            user.setCi(ci);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            return user;
        }

        // Getters y Setters
        public Integer getCi() {
            return ci;
        }

        public void setCi(Integer ci) {
            this.ci = ci;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class UpdateUser {
        private String name;
        private String email;
        private String phone;

        // Getters y Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}