package com.spring.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.mvc.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}