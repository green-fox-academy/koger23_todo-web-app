package com.greenfoxacademy.listing_todo_mysql.service;

import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import com.greenfoxacademy.listing_todo_mysql.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

  private TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> findAll() {
    List<Todo> todoList = new ArrayList<>();
    todoRepository.findAll().forEach(todoList::add);
    return todoList.stream().sorted(Comparator.comparing(todo -> todo.getId())).collect(Collectors.toList());
  }

  public List<Todo> findActive() {
    List<Todo> filteredList = findAll().stream().filter(todo -> !todo.isDone()).collect(Collectors.toList());
    return filteredList;
  }

  public void add(Todo todo) {
    todoRepository.save(todo);
  }

  public void delete(long id) {
    todoRepository.deleteById(id);
  }

  public Todo findById(long id) {
    return todoRepository.findById(id)
            .orElse(new Todo());
  }

  public void update(long id, Todo newTodo) {
    Todo todo = todoRepository.findById(id).get();
    todo.setTitle(newTodo.getTitle());
    todo.setUrgent(newTodo.isUrgent());
    todo.setDone(newTodo.isDone());
    todoRepository.save(todo);
  }
}
