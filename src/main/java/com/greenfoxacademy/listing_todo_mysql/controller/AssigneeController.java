package com.greenfoxacademy.listing_todo_mysql.controller;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;
import com.greenfoxacademy.listing_todo_mysql.service.IAssigneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AssigneeController {

  private IAssigneeService assigneeService;

  public AssigneeController(IAssigneeService assigneeService) {
    this.assigneeService = assigneeService;
  }

  @GetMapping("/assignees")
  public String showAssignees(Model model) {
    model.addAttribute("assigneeList", assigneeService.findAll());
    return "/assignees";
  }

  @GetMapping("/assignee/delete/{id}")
  public String delete(@PathVariable long id) {
    assigneeService.delete(id);
    return "redirect:/assignees";
  }

  @GetMapping("/assignees/search")
  public String search(Model model, @RequestParam("search") String keyword) {
    model.addAttribute("assigneeList", assigneeService.search(keyword));
    return "/assignees";
  }

  @GetMapping("/assignee/{id}/edit_assignee")
  public String editAssignee(Model model, @PathVariable("id") long assignee_id) {
    model.addAttribute("assignee_id", assignee_id);
    model.addAttribute("assignee", assigneeService.findById(assignee_id));
    return "/edit_assignee";
  }

  @PostMapping("/assignee/{id}/save")
  public String saveAssignee(Model model, @PathVariable long id, @ModelAttribute Assignee newAssignee) {
    Assignee assignee = assigneeService.findById(id);
    assignee.setFirstName(newAssignee.getFirstName());
    assignee.setLastName(newAssignee.getLastName());
    assignee.setEmail(newAssignee.getEmail());;
    assigneeService.save(assignee);
    model.addAttribute("assigneeList", assigneeService.findAll());
    return "redirect:/assignees";
  }
}
