package com.example.usercrud.repository;

import com.example.usercrud.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByPhoneAndPassword(String email, String password);

    Optional<UserEntity> findByEmail(String email);
}
