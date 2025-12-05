package com.example.POS.Repository;

import com.example.POS.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
   Optional<User> findByFirstName(String firstName);
   //Optinal why means here if no user it may give null
   Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);}
