package com.jotave.todolist.services;

import com.jotave.todolist.models.Task;
import com.jotave.todolist.models.User;
import com.jotave.todolist.repositories.TaskRepository;
import com.jotave.todolist.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public List<Task> findAllByUserId(Long userId) {
        List<Task>  tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task task) {
        User user = this.userService.findById(task.getUser().getId());

        task.setId(null);
        task.setUser(user);
        task = this.taskRepository.save(task);

        return task;
    }

    @Transactional
    public Task update(Task task) {
        Task newTask = this.findById(task.getId());
        newTask.setDescription(task.getDescription());
        return this.taskRepository.save(newTask);
    }

    public void delete(Long id) {
        this.findById(id);

        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar tarefa");
        }
    }
}
