package com.greenfoxacademy.listing_todo_mysql.controller;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;
import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import com.greenfoxacademy.listing_todo_mysql.service.IAssigneeService;
import com.greenfoxacademy.listing_todo_mysql.service.ITodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

  private ITodoService todoServiceImp;
  private IAssigneeService assigneeService;

  public TodoController(ITodoService todoServiceImp, IAssigneeService assigneeService) {
    this.todoServiceImp = todoServiceImp;
    this.assigneeService = assigneeService;
  }

  @GetMapping("/todo")
  public String list(Model model, @RequestParam(required = false) boolean isActive) {
    if (isActive) {
      model.addAttribute("todolist", todoServiceImp.findActive());
    } else {
      model.addAttribute("todolist", todoServiceImp.findAll());
    }
    return "todo";
  }

  @GetMapping("/todo/add")
  public String addForm(Model model) {
    model.addAttribute("assignee_list", assigneeService.findAll());
    model.addAttribute("todo", new Todo());
    return "add";
  }

  @PostMapping("/todo/add")
  public String add(@ModelAttribute Todo todo, @RequestParam("selection") String assigneeName) {
    String[] assigneeDatas = assigneeName.split(" ");
    Assignee assignee = assigneeService.findAllByFirstNameAndLastNameAndEmail(assigneeDatas[0], assigneeDatas[1], assigneeDatas[2]);
    todo.setAssignee(assignee);
    todoServiceImp.add(todo);
    return "redirect:/todo";
  }

  @GetMapping("todo/delete/{id}")
  public String delete(@PathVariable long id) {
    todoServiceImp.delete(id);
    return "redirect:/todo";
  }

  @GetMapping("/todo/edit/{id}")
  public String renderEditForm(Model model, @PathVariable long id) {
    Todo todo = todoServiceImp.findById(id);
    model.addAttribute("assignee_list", assigneeService.findAll());
    model.addAttribute("id", id);
    model.addAttribute("todo", todo);
    return "edit";
  }

  @PostMapping("/todo/edit/{id}")
  public String saveTodo(Todo newTodo, @PathVariable long id, @RequestParam String selection) {
    String[] name = selection.split(" ");
    todoServiceImp.update(id, newTodo, name[0], name[1]);
    return "redirect:/todo";
  }

  @GetMapping("/todo/{id}/details")
  public String showDetails(Model model, @PathVariable long id) {
    Todo todo = todoServiceImp.findById(id);
    model.addAttribute("todo", todo);
    return "details";
  }

  @GetMapping("/todo/search")
  public String search(Model model, @RequestParam("keyword") String keyword) {
    if (keyword.isEmpty()) {
      model.addAttribute("todolist", todoServiceImp.findAll());
    } else {
      List<Todo> todoList = todoServiceImp.search(keyword);
      model.addAttribute("todolist", todoList);
    }
    return "/todo";
  }

  @GetMapping("/todo/{todo_id}/addassignee")
  public String addAssignee(Model model, @PathVariable long todo_id) {
    model.addAttribute("assignee", new Assignee());
    model.addAttribute("todo_id", todo_id);
    return "addassignee";
  }

  @PostMapping("/todo/{todo_id}/createassignee")
  public String createAssignee(Model model, String firstName, String lastName, String email, @PathVariable long todo_id) {
    Assignee assignee = new Assignee(firstName, lastName, email);
    assignee.getTodoList().add(todoServiceImp.findById(todo_id));
    assigneeService.save(assignee);
    Todo todo = todoServiceImp.findById(todo_id);
    todo.setAssignee(assignee);
    todoServiceImp.save(todo);
    model.addAttribute("todolist", todoServiceImp.findAll());
    return "redirect:/todo";
  }
}