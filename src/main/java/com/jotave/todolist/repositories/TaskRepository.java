package com.jotave.todolist.repositories;

import com.jotave.todolist.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // o _ é para dizer que é o id do usuário, porque o JPA não entende que é o id do usuário
    List<Task> findByUser_Id(Long id);

    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    //List<Task> findByUserId(@Param("id") Long id);
}
