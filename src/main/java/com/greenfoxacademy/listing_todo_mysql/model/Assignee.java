package com.greenfoxacademy.listing_todo_mysql.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Assignee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee")
  private List<Todo> todoList;

  public Assignee() {
    this.todoList = new ArrayList<>();
  }

  public Assignee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.todoList = new ArrayList<>();
  }
}
