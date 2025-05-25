package com.task.taskTracker.controller;

import com.task.taskTracker.model.Task;
import com.task.taskTracker.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService = new TaskService();
    @PostMapping
    public String addTask(@RequestBody String description){
        taskService.addTask(description);
        return "Task added.";
    }
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.findByStatus("all");
    }
    @GetMapping("/{status}")
    public List<Task> getTaskByStatus(@PathVariable String status){
        return taskService.findByStatus(status);
    }
    @PutMapping("/{id}")
    public String updateTask(@PathVariable int id, @RequestBody String newDescription){
        taskService.updateTask(id, newDescription);
        return "Task updated.";
    }
    @PutMapping("/{id}/done")
    public String markDone(@PathVariable int id){
        taskService.markStatus(id, "done");
        return "Marked as done.";
    }
    @PutMapping("/{id}/progress")
    public String markProgress(@PathVariable int id){
        taskService.markStatus(id, "in-progress");
        return "Marked as in progress.";
    }
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
        return "Task deleted.";
    }
}
