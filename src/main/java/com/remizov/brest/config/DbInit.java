package com.remizov.brest.config;

import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import com.remizov.brest.repository.ElementRepository;
import com.remizov.brest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DbInit {

	@Autowired
    TaskRepository taskRepository;
	@Autowired
    ElementRepository elementRepository;

	@PostConstruct
	void init() {
		Task testedTask = new Task("Just do it",
				"12300",
				"some description",
				LocalDate.of(2022,12,12),
				LocalDate.of(2022,12,25),2);
		testedTask.setElement((new Element("el0","some descr0","value0",testedTask)));

		Task testedTask1 = new Task("name1",
				"123001",
				"some description1",
				LocalDate.of(2021,12,12),
				LocalDate.of(2022,12,12),2);
		testedTask1.setElement((new Element("el1","some descr1","value1",testedTask1)));
		testedTask1.setElement(new Element("el12","some descr12","value12",testedTask1));
		Task savedTask1 = taskRepository.save(testedTask1);

		Task testedTask2 = new Task("name2",
				"123002",
				"some description2",
				LocalDate.of(2022,12,12),
				LocalDate.of(2023,12,13),3);
		testedTask.setElement((new Element("el2","some descr2","value2",testedTask2)));
		testedTask.setElement((new Element("el22","some descr22","value22",testedTask2)));
		Task savedTask2 = taskRepository.save(testedTask2);
	}
}
