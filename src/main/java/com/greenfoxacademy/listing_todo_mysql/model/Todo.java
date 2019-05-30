package com.greenfoxacademy.listing_todo_mysql.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  @Column(nullable = false, name = "Title")
  private String title;
  @Column(nullable = false, name = "Urgent")
  private boolean urgent;
  @Column(nullable = false, name = "Done")
  private boolean done;
  @Column(nullable = false, name = "Created_on")
  @Temporal(TemporalType.DATE)
  private Date date;
  @Column(nullable = false, name = "Created_at")
  @Temporal(TemporalType.TIME)
  private Date time;
  @ManyToOne(fetch = FetchType.LAZY)
  private Assignee assignee;

  public Todo(){
  }

  public Todo(String title) {
    this(title, false, false, new Date(), new Date(), new Assignee());
  }

  public Todo(String title, boolean urgent, boolean done, Date date, Date time, Assignee assignee) {
    this.title = title;
    this.urgent = urgent;
    this.done = done;
    this.date = date;
    this.time = time;
    this.assignee = assignee;
  }
}

