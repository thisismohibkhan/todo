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

import com.nafys.todo.model.Todo;
import com.nafys.todo.service.TodoService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodosController {
	@Autowired
	private TodoService todoService;
	
	@GetMapping("list-todos")
	public String listTodos(ModelMap model) {
		List<Todo> todos = todoService.getAllTodos();
		model.addAttribute("todos", todos);
		return "list-todos";
	}
	
	@GetMapping("add-todo")
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(0l, username, "", null, false);
		model.put("todo", todo);
		return "add-todo";
	}
	
	@PostMapping("add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result ) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		String username = (String)model.get("name");
		todoService.addTodo(username, todo.getDescription(), 
				todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	@GetMapping("delete-todo")
	public String deleteTodo(@RequestParam Long id) {
		//Delete todo
		
		todoService.deleteById(id);
		return "redirect:list-todos";
		
	}

	@GetMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam Long id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "add-todo";
	}
	
	@PostMapping("update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result ) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		String username = (String)model.get("name");
		todo.setUsername(username);
		System.out.println(todo);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
}
