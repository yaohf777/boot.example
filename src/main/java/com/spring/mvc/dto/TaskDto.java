package com.spring.mvc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private String id;
    private String title;
    private String description;
    private String status;

    public TaskDto(String id, String title, String description, String status) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}