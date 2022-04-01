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
class ElementRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ElementRepositoryTest.class);

    private Element testedElement;
    private Task testedTask;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ElementRepository elementRepository;

    @BeforeEach
     void setUp() {
        log.debug("create test Element");
        testedTask = taskRepository.save(new Task("name",
                "12300",
                "some description",
                LocalDate.of(2022,12,12),
                LocalDate.of(2022,12,25),1));

        testedTask.setElement(elementRepository.
                save(new Element("el0","some descr0","value0",testedTask)));

        testedElement = new Element("el1","some descr1","value1",testedTask);

    }

    @Test
    void  shouldCreateTestedObject(){
        log.debug("shouldCreateTestedObject()");
        assertNotNull(testedElement);
        assertEquals("el1",testedElement.getName());
        assertNotNull(elementRepository);
        assertEquals(1,elementRepository.findAll().size());
    }
}