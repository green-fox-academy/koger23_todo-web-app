package com.greenfoxacademy.listing_todo_mysql.service;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;

import java.util.List;

public interface IAssigneeService {
  List<Assignee> findAll();

  void add(Assignee assignee);

  void delete(long id);

  Assignee findById(long id);

  void update(long id, Assignee newAssignee, String firstname, String lastname);

  List<Assignee> search(String keyword);

  void save(Assignee assignee);

  Assignee findAllByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
