package com.springproject.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.curso.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
