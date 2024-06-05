package com.test.orderproject.domain.member.dao;

import com.test.orderproject.domain.member.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
