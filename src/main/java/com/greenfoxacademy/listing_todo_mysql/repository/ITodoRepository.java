package com.greenfoxacademy.listing_todo_mysql.repository;

import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface ITodoRepository extends CrudRepository<Todo, Long> {
}
