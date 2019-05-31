package com.greenfoxacademy.listing_todo_mysql.repository;

import com.greenfoxacademy.listing_todo_mysql.model.Assignee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IAssigneeRepository extends CrudRepository<Assignee, Long> {
  Assignee findByFirstNameAndLastName(String first_name, String last_name);

  @Query("select a from Assignee a where a.lastName LIKE CONCAT('%',?1,'%') or a.firstName LIKE CONCAT('%',?1,'%') or a.email LIKE CONCAT('%',?1,'%')")
  List<Assignee> findAllByFirstNameIsLikeAnOrLastNameIsLike(String keyword);

  @Query("select a from Assignee a where a.lastName=?1 and a.firstName=?2 and a.email=?3")
  Assignee findAllByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
