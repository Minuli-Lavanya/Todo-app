package com.example.todo.application.controllers;

import com.example.todo.application.models.Task;
import com.example.todo.application.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Allow requests from Angular app

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Task> getTasks() {
        return taskService.getRecentTasks();
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}/complete")
    public void completeTask(@PathVariable Long id) {
        taskService.markTaskAsCompleted(id);
    }
}
