package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    DbService dbService;

    @Test
    public void getAllTasksTest() {

        //Given & When
        List<Task> listOfAllTasksPrevious = dbService.getAllTasks();

        Task task = new Task(50L,"a","a");
        dbService.saveTask(task);
        dbService.deleteTask(task.getId());

        List<Task> listOffAllTaskAfter = dbService.getAllTasks();

        //Then
        Assertions.assertEquals(listOfAllTasksPrevious.size(),listOffAllTaskAfter.size());


    }



}
