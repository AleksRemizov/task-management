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

    @Test
    void shouldSaveElement(){
        log.debug("shouldSaveElement()");

        int sizeBefore = elementRepository.findAll().size();
        var element = elementRepository.save(
               new Element("test","some text","some value",testedTask));
        int sizeAfter = elementRepository.findAll().size();

        assertTrue(elementRepository.findById(element.getId()).isPresent());
        assertEquals(sizeBefore + 1,sizeAfter);
    }

    @Test
    void shouldDeleteElement(){
        log.debug("shouldDeleteElement()");
        int sizeBefore = (int) elementRepository.count();
        Element element = elementRepository.findAll().get(0);

        elementRepository.removeById(element.getId());

        int sizeAfter = (int) elementRepository.count();

        assertFalse(elementRepository.existsById(element.getId()));
        assertEquals(sizeBefore,sizeAfter + 1);
    }

    @Test
    void shouldUpdateElement(){
        log.debug("shouldUpdateElement()");
        Element element = elementRepository.findAll().get(0);
        element.setName(testedElement.getName());
        element.setDescription(testedElement.getDescription());
        element.setValue("newValue");
        Element savedElement = elementRepository.save(element);

        assertTrue(elementRepository.existsById(savedElement.getId()));
        assertEquals(element.getId(),savedElement.getId());
        assertEquals(testedElement.getName(),savedElement.getName());
        assertEquals(testedElement.getTask(),savedElement.getTask());
        assertEquals(testedElement.getDescription(),savedElement.getDescription());
        assertEquals("newValue",savedElement.getValue());
    }

    @Test
    void shouldFindAll(){
        log.debug("shouldUpdateElement()");

        List<Element> elements = elementRepository.findAll();

        assertNotNull(elements);
        assertFalse(elements.isEmpty());
        assertEquals(1,elements.size());
    }

    @Test
    void shouldFindByValueContainingIgnoreCase(){
        log.debug("shouldFindByValueContainingIgnoreCase()");
        Element savedElement1 = elementRepository.save(testedElement);
        Element savedElement2 = elementRepository.save(
                new Element("el1","some descr1","unique",testedTask));

        assertEquals(3,elementRepository.findAll().size());
        assertEquals(2,elementRepository.findByValueContainingIgnoreCase("value").size());
        assertEquals(2,elementRepository.findByValueContainingIgnoreCase("VA").size());
        assertEquals(1,elementRepository.findByValueContainingIgnoreCase("1").size());
        assertEquals(1,elementRepository.findByValueContainingIgnoreCase("0").size());
        assertEquals(1,elementRepository.findByValueContainingIgnoreCase("unique").size());
        assertEquals(0,elementRepository.findByValueContainingIgnoreCase("Test").size());
    }

    @Test
    void shouldFindByTask(){
        log.debug("shouldFindByTask()");
        Task testedTask1 = taskRepository.save(new Task("newName"));
        testedTask1.addElement(elementRepository.save(
                new Element("el1","some descr1","unique",testedTask1)
        ));
        testedTask1.addElement(elementRepository.save(
                new Element("el2","some descr2","unique2",testedTask1)
        ));
        assertEquals(3,elementRepository.findAll().size());
        assertEquals(2,elementRepository.findByTaskId(testedTask1.getId()).size());
        assertEquals(1,elementRepository.findByTaskId(testedTask.getId()).size());
    }
}