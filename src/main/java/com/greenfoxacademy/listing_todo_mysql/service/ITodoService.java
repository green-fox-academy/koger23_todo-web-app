package com.greenfoxacademy.listing_todo_mysql.service;

import com.greenfoxacademy.listing_todo_mysql.model.Todo;

import java.util.List;

public interface ITodoService {
  List<Todo> findAll();

  List<Todo> findActive();

  void add(Todo todo);

  void delete(long id);

  Todo findById(long id);

  void update(long id, Todo newTodo, String firstname, String lastname);

  List<Todo> search(String title);

  void save(Todo todo);
}
