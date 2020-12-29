package com.kyurao.atio.repository;

import com.kyurao.atio.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountEmail(String username);

    Optional<User> findByAccountActivationCode(String code);

    boolean existsByAccountEmailIgnoreCase(String email);
}
