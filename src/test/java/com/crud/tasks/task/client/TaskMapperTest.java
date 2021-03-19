package com.crud.tasks.task.client;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private com.crud.tasks.mapper.TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {

        //Given
        TaskDto taskDto = new TaskDto(1L,"First title","First Description");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assertions.assertEquals(task.getId(),1L);
        Assertions.assertEquals(task.getTitle(),"First title");
        Assertions.assertEquals(task.getContent(),"First Description");

    }

    @Test
    public void mapToTaskDtoTest() {

        //Given
        Task task = new Task(1L,"First title","First Description");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assertions.assertEquals(taskDto.getId(),1L);
        Assertions.assertEquals(taskDto.getTitle(),"First title");
        Assertions.assertEquals(taskDto.getContent(),"First Description");

    }

    @Test
    public void mapToTaskDtoListTest() {

        //Given
        Task task = new Task(1L,"First title","First Description");
        List<Task> taskList = List.of(task);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assertions.assertEquals(taskDtoList.size(),1);
        Assertions.assertEquals(taskDtoList.get(0).getId(),1L);
        Assertions.assertEquals(taskDtoList.get(0).getTitle(),"First title");
        Assertions.assertEquals(taskDtoList.get(0).getContent(),"First Description");
    }


}
