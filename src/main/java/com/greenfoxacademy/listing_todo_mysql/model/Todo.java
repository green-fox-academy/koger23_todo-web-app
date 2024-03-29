package com.greenfoxacademy.listing_todo_mysql.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
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
  @Column(name = "dueDate")
  private java.sql.Date dueDate = new java.sql.Date(System.currentTimeMillis());
  @ManyToOne(fetch = FetchType.LAZY)
  private Assignee assignee;

  public Todo(){
  }

  public Todo(String title) {
    this(title, false, false, new Date(), new Date(), (java.sql.Date) new Date(), new Assignee());
  }

  public Todo(String title, boolean urgent, boolean done, Date date, Date time, java.sql.Date dueDate, Assignee assignee) {
    this.title = title;
    this.urgent = urgent;
    this.done = done;
    this.date = date;
    this.time = time;
    this.dueDate = dueDate;
    this.assignee = assignee;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isUrgent() {
    return urgent;
  }

  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public java.sql.Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(@DateTimeFormat(pattern = "yyyy-MM-dd") java.sql.Date date) {
    this.dueDate = date;
  }

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }
}

