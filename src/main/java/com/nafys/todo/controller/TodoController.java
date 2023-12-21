package com.nafys.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nafys.todo.Repository.TodoRepository;
import com.nafys.todo.model.Todo;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {
	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("list-todos")
	public String listTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "list-todos";
	}

	@GetMapping("add-todo")
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo();
		todo.setUsername(username);
		model.put("todo", todo);
		return "add-todo";
	}

	@PostMapping("add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "add-todo";
		}
		String username = (String) model.get("name");
		todo.setUsername(username);
		todo.setDone(false);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}

	@GetMapping("delete-todo")
	public String deleteTodo(@RequestParam Long id) {
		// Delete todo

		todoRepository.deleteById(id);
		return "redirect:list-todos";

	}

	@GetMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam Long id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "add-todo";
	}

	@PostMapping("update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "add-todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		return (String)model.get("name");
	}

}
