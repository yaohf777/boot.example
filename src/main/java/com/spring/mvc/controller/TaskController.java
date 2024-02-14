package com.spring.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.dto.TaskDto;
import com.spring.mvc.model.Task;
import com.spring.mvc.model.TaskStatus;
import com.spring.mvc.repository.TaskRepository;

@RestController
@RequestMapping(TaskController.PATH)
public class TaskController {

    @Autowired
    private TaskRepository repository;

    public static final String PATH = "/tasks";

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    // 1. Create task
    public String createTask(@RequestBody TaskDto taskDto) {

        Task task = convertToEntity(taskDto);
        Task taskCreated = repository.save(task);
        return taskCreated.getId().toString();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    // 2. Get one task
    public TaskDto getTask(@PathVariable("id") Long id, HttpServletResponse response) {

        Task task = repository.findById(id).orElse(null);
        if (task == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        }
        response.setStatus(HttpStatus.OK.value());
        return task.toDto();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    // 3. Update task
    public TaskDto updatePost(@PathVariable("id") Long taskId, @RequestBody TaskDto taskDto,
            HttpServletResponse response) {

        Task task = repository.findById(taskId).orElse(null);
        if (task == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        }

        // Validate task status
        boolean validStatus = false;
        for (TaskStatus status : TaskStatus.values()) {
            if (status.toString().equals(taskDto.getStatus())) {
                validStatus = true;
                break;
            }
        }
        if (!validStatus) {

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            String responseToClient = "Available statuses are: CREATED, APPROVED, REJECTED, BLOCKED, DONE.";
            try {
                response.getWriter().write(responseToClient);
                response.getWriter().flush();
            } catch (IOException e) {
                System.out.println("Exception with write message" + e.getMessage());
            }
            return null;
        }

        response.setStatus(HttpStatus.OK.value());
        return task.toDto();
    }

    @DeleteMapping(value = "/{id}")
    // 4. Delete task
    public void deleteTask(@PathVariable("id") Long taskId, HttpServletResponse response) {

        Task task = repository.findById(taskId).orElse(null);
        if (task == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return;
        }

        repository.delete(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    // 5. Find all tasks
    public List<TaskDto> getTaskss() {

        List<TaskDto> taskList = new ArrayList<TaskDto>();
        Iterable<Task> taskIterable = repository.findAll();
        if (taskIterable != null) {
            for (Task task : taskIterable) {
                taskList.add(task.toDto());
            }
        }
        return taskList;
    }

    private Task convertToEntity(TaskDto taskDto) {

        Task task = new Task(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        return task;
    }
}
