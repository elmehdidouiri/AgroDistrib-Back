package org.example.miniprojetback.Repositories;


import org.example.miniprojetback.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


}
