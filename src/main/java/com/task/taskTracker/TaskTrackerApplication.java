package com.task.taskTracker;

import com.task.taskTracker.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskTrackerApplication implements CommandLineRunner {
	private final TaskService taskService = new TaskService();
	public static void main(String[] args)
	{
		SpringApplication.run(TaskTrackerApplication.class, args);
	}
	@Override
	public void run(String... args){
		if (args.length == 0){
			System.out.println("No command provided.");
			return;
		}
		String command = args[0];
		try{
			switch (command){
				case "add":
					taskService.addTask(joinArgs(args,1));
					break;
				case "update":
					taskService.updateTask(Integer.parseInt(args[1]), joinArgs(args,2));
					break;
				case "delete":
					taskService.deleteTask(Integer.parseInt(args[1]));
					break;
				case "mark-done":
					taskService.markStatus(Integer.parseInt(args[1]), "done");
					break;
				case "mark-in-progress":
					taskService.markStatus(Integer.parseInt(args[1]), "in-progress");
					break;
				case "list":
					if(args.length == 1){
						taskService.list("all");
					}
					else {
						taskService.list(args[1]);
					}
					break;
				default:
					System.out.println("Unknown Command");
			}

		}
		catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}
	}
	private String joinArgs(String[] args, int startIndex){
		StringBuilder sb = new StringBuilder();
		for(int i = startIndex;i<args.length;i++){
			sb.append(args[i]);
			if(i < args.length - 1) sb.append(" ");
		}
		return sb.toString();
	}

}
