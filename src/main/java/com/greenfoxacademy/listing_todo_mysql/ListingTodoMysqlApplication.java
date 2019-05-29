package com.greenfoxacademy.listing_todo_mysql;

import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import com.greenfoxacademy.listing_todo_mysql.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ListingTodoMysqlApplication implements CommandLineRunner {
  private TodoRepository todoRepository;

  public ListingTodoMysqlApplication(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(ListingTodoMysqlApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
