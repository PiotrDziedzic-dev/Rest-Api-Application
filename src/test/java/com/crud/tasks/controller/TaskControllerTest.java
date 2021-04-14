package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getTaskListTest() throws Exception {

        //Given
        List<Task> taskList = List.of(new Task(1L,"First title","First Description"));
        List<TaskDto> taskDtoList = List.of(new TaskDto(1L,"First title","First Description"));
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then

        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
    @Test
    public void deleteTaskTest() throws Exception {

        //Given
        Task task = new Task(99L,"First title","First Description");
        dbService.saveTask(task);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/99"))
                        .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dbService).deleteTask(99L);

    }


    @Test
    public void getTaskTest() throws Exception {

        //Given
        Task task = new Task(1L,"First title","First Description");
        TaskDto taskDto = new TaskDto(1L,"First title","First Description");
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("First title")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("First Description")));
    }

    @Test
    public void createTaskTest() throws Exception {

        //Given
        Task task = new Task(1L,"First title","First Description");
        TaskDto taskDto = new TaskDto(1L,"Random title","Random content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenReturn(task);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                        .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dbService).saveTask(task);


    }
    @Test
    public void updateTaskTest() throws Exception {

        //Given
        Task task = new Task(1L,"First title","First Description");
        TaskDto taskDto = new TaskDto(1L,"First title","First Description");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                        .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dbService).saveTask(task);

    }


}