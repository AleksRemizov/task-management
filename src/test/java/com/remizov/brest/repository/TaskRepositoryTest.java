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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class TaskRepositoryTest {


    private static final Logger log = LoggerFactory.getLogger(TaskRepositoryTest.class);

    @Autowired
    TaskRepository taskRepository;

    private Task testedTask;

    @BeforeEach
    public void setUp() {
       log.debug("create test entity");
         testedTask = new Task("Just do it",
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

    @Test
    void shouldDeleteTaskWithElements(){
        log.debug("shouldDeleteTaskWithElements()");

        Task savedTask = taskRepository.save(testedTask);
        assertEquals(3,savedTask.getElements().size());
        int repoSizeBefore = taskRepository.findAll().size();

        taskRepository.deleteById(savedTask.getId());
        int repoSizeAfter = taskRepository.findAll().size();

        assertEquals(repoSizeBefore - 1,repoSizeAfter );
        assertFalse(taskRepository.existsById(savedTask.getId()));
    }

    @Test
    void shouldUpdateTask(){
        log.debug("shouldUpdateTask()");

        Task beforeUpdate = taskRepository.findAll().get(0);
        assertEquals(2,taskRepository.findAll().size());

        beforeUpdate.setPassword(testedTask.getPassword());
        beforeUpdate.setName(testedTask.getName());
        beforeUpdate.setDescription("shouldPossibleUpdate");

        Task updatedTask = taskRepository.save(beforeUpdate);


        assertEquals(2,taskRepository.findAll().size());
        assertEquals(beforeUpdate.getId(),updatedTask.getId());
        assertEquals(testedTask.getName(),updatedTask.getName());
        assertEquals(testedTask.getPassword(),updatedTask.getPassword());
        assertEquals("shouldPossibleUpdate",updatedTask.getDescription());



    }
    @Test
    void shouldFindAll() {
        log.debug("shouldFindAll()");

        List<Task> tasks = taskRepository.findAll();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(2,tasks.size());
    }

    @Test
    void shouldFindByNameContaining(){
        log.debug("shouldFindByNameContaining()");

        Task savedTask = taskRepository.save(testedTask);
        assertEquals(3,taskRepository.findAll().size());
        // name containing "name"
        assertEquals(2,taskRepository.findByNameContainingIgnoreCase("name").size());
        // name containing "1"
        assertEquals(1,taskRepository.findByNameContainingIgnoreCase("1").size());
        // name containing "Just"
        assertEquals(1,taskRepository.findByNameContainingIgnoreCase("Just").size());
        // name containing "just"
        assertEquals(1,taskRepository.findByNameContainingIgnoreCase("just").size());
        //  negative scenario name containing "task"
        assertTrue(taskRepository.findByNameContainingIgnoreCase("task").isEmpty());
    }

    @Test
    void shouldFindByStatusLike(){
        log.debug("shouldFindByNameContaining()");

        assertEquals(1,taskRepository.findByStatus(2).size());
        assertEquals(1,taskRepository.findByStatus(3).size());

        Task savedTask = taskRepository.save(testedTask);

        assertEquals(2,taskRepository.findByStatus(2).size());
        assertTrue(taskRepository.findByStatus(1).isEmpty());
        assertTrue(taskRepository.findByStatus(0).isEmpty());
        assertTrue(taskRepository.findByStatus(137).isEmpty());
    }

    @Test
    void shouldFindByStartDateGreaterThanEqualAndEndDateLessThanEqual(){
        log.debug("shouldFindByStartDateGreaterThanEqualAndEndDateLessThanEqual()");
        LocalDate startDate = LocalDate.of(2022,12,12);
        LocalDate endDate = LocalDate.of(2023,12,13);

        assertEquals(2,taskRepository.findAll().size());

        assertEquals(1,
                taskRepository.
                        searchBetweenDates(
                                startDate
                                ,endDate).size());
        Task savedTask = taskRepository.save(testedTask);

        assertEquals(2,
                taskRepository.
                        searchBetweenDates(
                                startDate
                                ,endDate).size());

        assertEquals(0,
                taskRepository.
                        searchBetweenDates(
                                startDate.plusDays(1)
                                ,endDate).size());

        assertEquals(1,
                taskRepository.
                        searchBetweenDates(
                                LocalDate.of(2021,12,12)
                                ,startDate).size());

        assertEquals(0,
                taskRepository.
                        searchBetweenDates(
                                LocalDate.of(2021,12,12)
                                ,startDate.minusDays(1)).size());

    }
}