package com.jotave.todolist.repositories;

import com.jotave.todolist.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<User, Long> é uma interface que já possui métodos prontos para manipular o banco de dados
public interface UserRepository extends JpaRepository<User, Long> {
    // Já possui métodos como save, findAll, findById, deleteById, etc.
}
