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

  @Override
  public void add(Assignee assignee) {
    assigneeRepository.save(assignee);
  }

  @Override
  public void delete(long id) {
    assigneeRepository.deleteById(id);
  }

  @Override
  public Assignee findById(long id) {
    return assigneeRepository.findById(id).orElse(new Assignee());
  }

  @Override
  public void update(long id, Assignee newAssignee, String firstName, String lastName) {
    Assignee assignee = assigneeRepository.findByFirstNameAndLastName(firstName, lastName);
    assignee.setFirstName(newAssignee.getFirstName());
    assignee.setLastName(newAssignee.getLastName());
    assigneeRepository.save(assignee);
  }

  @Override
  public List<Assignee> search(String keyword) {
    return assigneeRepository.findAllByFirstNameIsLikeAnOrLastNameIsLike(keyword);
  }

  public Assignee findAllByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email) {
    return assigneeRepository.findAllByFirstNameAndLastNameAndEmail(firstName, lastName, email);
  }
}
