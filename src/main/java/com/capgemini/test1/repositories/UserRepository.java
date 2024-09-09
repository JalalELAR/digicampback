package com.capgemini.test1.repositories;

import com.capgemini.test1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
