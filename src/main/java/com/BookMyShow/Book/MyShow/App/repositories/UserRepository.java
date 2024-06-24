package com.BookMyShow.Book.MyShow.App.repositories;

import com.BookMyShow.Book.MyShow.App.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
