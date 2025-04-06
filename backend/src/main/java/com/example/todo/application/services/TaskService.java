package com.example.todo.application.services;

import com.example.todo.application.Repository.TaskRepository;
import com.example.todo.application.models.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getRecentTasks() {
        return taskRepository.findTop5ByCompletedFalseOrderByIdDesc();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void markTaskAsCompleted(Long id) {
        taskRepository.findById(id).ifPresent(task -> {
            task.setCompleted(true);
            taskRepository.save(task);
        });
    }

}
