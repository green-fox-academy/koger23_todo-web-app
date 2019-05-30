package com.greenfoxacademy.listing_todo_mysql.service;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;
import com.greenfoxacademy.listing_todo_mysql.repository.IAssigneeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssigneeServiceImp implements IAssigneeService {
  private IAssigneeRepository assigneeRepository;

  public AssigneeServiceImp(IAssigneeRepository assigneeRepository) {
    this.assigneeRepository = assigneeRepository;
  }

  @Override
  public void save(Assignee assignee) {
    assigneeRepository.save(assignee);
  }

  @Override
  public List<Assignee> findAll() {
    List<Assignee> assigneeList = new ArrayList<>();
    assigneeRepository.findAll().forEach(assigneeList::add);
    return assigneeList;
  }
}
