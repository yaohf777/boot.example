package com.spring.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

import com.spring.mvc.dto.TaskDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
    
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private TaskStatus status = TaskStatus.CREATED;

    public Task(String title) {
        this.title = title;
    }

    public TaskDto toDto() {
       return new TaskDto(String.valueOf(id), title, description, status.name());
    }
}
