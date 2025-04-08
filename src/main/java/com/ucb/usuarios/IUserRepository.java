package com.ucb.usuarios;

import com.ucb.usuarios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    boolean existsByCi(Integer ci);
    boolean existsByEmail(String email);
}
