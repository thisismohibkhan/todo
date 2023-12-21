package com.nafys.todo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nafys.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

	List<Todo> findByUsername(String username);

}
