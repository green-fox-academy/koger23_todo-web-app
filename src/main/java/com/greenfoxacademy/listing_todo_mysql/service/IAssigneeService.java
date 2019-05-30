package com.greenfoxacademy.listing_todo_mysql.service;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;

import java.util.List;

public interface IAssigneeService {
  void save(Assignee assignee);
  List<Assignee> findAll();
}
