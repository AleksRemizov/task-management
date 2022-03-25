package com.remizov.brest.repository;


import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
class TaskRepositoryTest {


    private static final Logger log = LoggerFactory.getLogger(TaskRepositoryTest.class);

    @Autowired
    TaskRepository taskRepository;

    private Task testedTask;
    private Task savedTask1;
    private Task savedTask2;

    @BeforeEach
    public void setUp() {
       log.debug("create test entity");
         testedTask = new Task("name",
                "12300",
                "some description",
                LocalDate.of(2022,12,12),
                LocalDate.of(2022,12,25),1);
         testedTask.setElement((new Element("el0","some descr0","value0",testedTask)));

       Task testedTask1 = new Task("name1",
                "123001",
                "some description1",
                LocalDate.of(2021,11,11),
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

    @Test
    void  shouldCreateTestedObject(){
        log.debug("shouldCreateTestedObject()");
        assertNotNull(testedTask);
        assertEquals(2,taskRepository.findAll().size());
    }

    @Test
    void shouldSaveOneTask(){
        log.debug("shouldSaveOneTask()");

        int repoSizeBeforeSave = taskRepository.findAll().size();
        Task savedTask = taskRepository.save(testedTask);

        assertEquals(repoSizeBeforeSave + 1,taskRepository.findAll().size());
        assertEquals(testedTask.getName(),savedTask.getName());


    }
}