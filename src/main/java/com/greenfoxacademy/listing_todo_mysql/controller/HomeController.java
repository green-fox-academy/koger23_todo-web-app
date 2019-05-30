package com.greenfoxacademy.listing_todo_mysql.controller;

import com.greenfoxacademy.listing_todo_mysql.service.ITodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.greenfoxacademy.listing_todo_mysql.service.TodoServiceImp;

@Controller
public class HomeController {
  private ITodoService todoServiceImp;

  public HomeController(TodoServiceImp todoServiceImp) {
    this.todoServiceImp = todoServiceImp;
  }

  @GetMapping("/")
  public String list(Model model) {
    model.addAttribute("todolist", todoServiceImp.findAll());
    return "todo";
  }
}
