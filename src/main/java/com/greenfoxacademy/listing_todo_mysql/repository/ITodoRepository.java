package com.greenfoxacademy.listing_todo_mysql.repository;

import com.greenfoxacademy.listing_todo_mysql.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITodoRepository extends CrudRepository<Todo, Long> {
  @Query("select t from Todo t where t.title LIKE CONCAT('%',?1,'%')")
  List<Todo> findAllByTitleContains(String keyword);
}
