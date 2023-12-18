package com.nafys.todo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.nafys.todo.model.Todo;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static Long todosCount =0l;
	//Long id, String username, String description, LocalDate targetDate, Boolean done
	static {
		todos.add(new Todo(++todosCount,"mohib", "Learn Spring Boot", LocalDate.now(), false ));
		todos.add(new Todo(++todosCount,"mohib", "Learn AWS", LocalDate.now(), false ));
		todos.add(new Todo(++todosCount,"mohib", "Learn Docker", LocalDate.now(), false ));
	}
	
	public List<Todo> getAllTodos(){
		return todos;
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
	}

	public void deleteById(Long id) {
		//todo.getId() == id
		// todo -> todo.getId() == id
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(Long id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}

}
