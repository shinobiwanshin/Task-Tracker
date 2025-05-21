package com.task.taskTracker.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.task.taskTracker.model.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final File file = new File("task.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Task> tasks;

    public TaskRepository() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            if (file.exists()) {
                tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});
            } else {
                tasks = new ArrayList<>();
                saveAll(); // only if file doesn't exist
            }
        } catch (Exception e) {
            tasks = new ArrayList<>();
            System.out.println("Failed to load the tasks: " + e.getMessage());
        }
    }

    public List<Task> findAll() {
        return tasks;
    }

    public void saveAll() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
        } catch (Exception e) {
            System.out.println("Error saving the task: " + e.getMessage());
        }
    }
}