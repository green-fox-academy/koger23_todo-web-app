package com.greenfoxacademy.listing_todo_mysql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.greenfoxacademy.listing_todo_mysql.service.TodoService;

@Controller
public class HomeController {
  private TodoService todoService;

  public HomeController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/")
  public String list(Model model) {
    model.addAttribute("todolist", todoService.findAll());
    return "todo";
  }
}
