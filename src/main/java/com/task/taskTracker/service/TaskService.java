package com.task.taskTracker.service;

import com.task.taskTracker.model.Task;
import com.task.taskTracker.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository repo;
    public TaskService(){
        this.repo = new TaskRepository();
    }
    public void addTask(String description){
        List<Task> tasks = repo.findAll();
        int newId = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        Task task = new Task();
        task.setId(newId);
        task.setDescription(description);
        task.setStatus("todo");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        tasks.add(task);
        repo.saveAll();
        System.out.println("Task Saved Successfully (ID : " + newId + " )");

    }
    public void updateTask(int id, String newDesc){
        Optional<Task> match = repo.findAll().stream().filter(t -> t.getId() == id).findFirst();
        if(match.isPresent()){
            Task task = match.get();
            task.setDescription(newDesc);
            task.setUpdatedAt(LocalDateTime.now());
            repo.saveAll();
            System.out.println("Task Updated!");
        }
        else{
            System.out.println("Task Not Found !!!");
        }

    }
    public void deleteTask(int id){
        List<Task> tasks = repo.findAll();
        boolean removed = tasks.removeIf(t-> t.getId() == id);
        if(removed){
            repo.saveAll();
            System.out.println("Task Deleted !");
        }
        else{
            System.out.println("Task Not Found!!!");
        }
    }
    public void markStatus(int id, String status){
        Optional<Task> match = repo.findAll().stream().filter(t->t.getId() == id).findFirst();
        if(match.isPresent()){
            Task task = match.get();
            task.setStatus(status);
            task.setUpdatedAt(LocalDateTime.now());
            repo.saveAll();
            System.out.println("Task marked as "+ status +".");
        }
        else{
            System.out.println("Task Not Found!!!");
        }
    }
    public void list(String filter){
        List<Task> task = repo.findAll();
        task.stream().filter(t->filter.equals("all") || t.getStatus().equalsIgnoreCase(filter)).forEach(this::printTask);
    }
    private void printTask(Task task){
        System.out.printf("ID: %d | [%s] %s\n", task.getId(), task.getStatus().toUpperCase(), task.getDescription());
    }
}
