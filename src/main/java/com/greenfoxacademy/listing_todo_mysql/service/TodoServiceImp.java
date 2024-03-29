package com.greenfoxacademy.listing_todo_mysql.service;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;
import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import com.greenfoxacademy.listing_todo_mysql.repository.IAssigneeRepository;
import com.greenfoxacademy.listing_todo_mysql.repository.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImp implements ITodoService {

  private ITodoRepository todoRepository;
  private IAssigneeRepository assigneeRepository;

  public TodoServiceImp(ITodoRepository todoRepository, IAssigneeRepository assigneeRepository) {
    this.todoRepository = todoRepository;
    this.assigneeRepository = assigneeRepository;
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
    todo.setDate(new Date());
    todo.setTime(new Date());
    todoRepository.save(todo);
  }

  public void delete(long id) {
    todoRepository.deleteById(id);
  }

  public Todo findById(long id) {
    return todoRepository.findById(id)
            .orElse(new Todo());
  }

  public void update(long id, Todo newTodo, String firstName, String lastName) {
    Todo todo = todoRepository.findById(id).get();
    todo.setTitle(newTodo.getTitle());
    todo.setUrgent(newTodo.isUrgent());
    todo.setDone(newTodo.isDone());
    todo.setDueDate(newTodo.getDueDate());
    Assignee assignee = assigneeRepository.findByFirstNameAndLastName(firstName, lastName);
    todo.setAssignee(assignee);

    todoRepository.save(todo);
  }

  @Override
  public List<Todo> search(String keyword) {
    return todoRepository.findAllByTitleContains(keyword);
  }

  @Override
  public void save(Todo todo) {
    todoRepository.save(todo);
  }
}
