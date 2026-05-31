package com.example.choudhary.EntityRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.choudhary.EntityApis.User;

public interface UserRepo extends JpaRepository<User, Integer> {

Optional<User> findByEmail(String email);

}
