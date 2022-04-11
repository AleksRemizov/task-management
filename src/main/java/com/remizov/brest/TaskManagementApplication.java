package com.remizov.brest;

import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import com.remizov.brest.repository.ElementRepository;
import com.remizov.brest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}
}
