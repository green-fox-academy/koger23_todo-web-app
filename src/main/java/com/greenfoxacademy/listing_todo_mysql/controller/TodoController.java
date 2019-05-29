package com.greenfoxacademy.listing_todo_mysql.controller;

import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import com.greenfoxacademy.listing_todo_mysql.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  private TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/todo")
  public String list(Model model, @RequestParam(required = false) boolean isActive) {
    if (isActive) {
      model.addAttribute("todolist", todoService.findActive());
    } else {
      model.addAttribute("todolist", todoService.findAll());
    }
    return "todo";
  }

  @GetMapping("/todo/add")
  public String addForm(Model model) {
    model.addAttribute("todo", new Todo());
    return "add";
  }

  @PostMapping("/todo/add")
  public String add(@ModelAttribute Todo todo) {
    todoService.add(todo);
    return "redirect:/todo";
  }

  @GetMapping("todo/delete/{id}")
  public String delete(@PathVariable long id) {
    todoService.delete(id);
    return "redirect:/todo";
  }

  @GetMapping("/todo/edit/{id}")
  public String edit(Model model, @PathVariable long id) {
    Todo todo = todoService.findById(id);
    model.addAttribute("id", id);
    model.addAttribute("todo", todo);
    return "edit";
  }

  @PostMapping("/todo/edit/{id}")
  public String edited(Todo newTodo, @PathVariable long id) {
    todoService.update(id, newTodo);
    return "redirect:/todo";
  }

  @GetMapping("/todo/{id}/details")
  public String showDetails(Model model, @PathVariable long id) {
    Todo todo = todoService.findById(id);
    model.addAttribute("todo", todo);
    return "details";
  }
}