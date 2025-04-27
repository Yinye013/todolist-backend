package com.todo.todolist.services;
import com.todo.todolist.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import com.todo.todolist.repositories.TodoRepository;
import java.util.*;
import org.springframework.stereotype.Service;



@Service // This annotation indicates that this class is a service class and will be used to perform business logic.
// This class is a service class that handles the business logic for the Todo application. It uses the TodoRepository class to interact with the database and perform CRUD operations on the Todo entity.
public class TodoServices {
	@Autowired // This annotation indicates that this class will be injected with the TodoRepository class.
	private final TodoRepository todoRepository; // This is the repository that will be used to interact with the database.
	public TodoServices(TodoRepository todoRepository) {
		this.todoRepository = todoRepository; // This is the constructor that will be used to inject the repository into the service.
	}

	// GET TODOS

	public List<Todo> getTodos() {
		return todoRepository.findAll(); // This method will return all the todos from the database.
	}
	// GET TODO BY ID
	public Todo getTodoById(Long id) {
		return todoRepository.findById(id).orElse(null); // This method will return the todo with the given id from the database.
	}

	// POST TODO
	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo); // This method will save the todo to the database and return it.
	}
	
	// UPDATE TODO (PUT)
 public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            Todo todo = existingTodo.get();
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todo.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(todo);
        } else {
            return null;
        }
    }
	// DELETE TODO (DELETE)	
	public void deleteTodo(Long id) {
		Todo existingTodo = todoRepository.findById(id).orElse(null); // This method will return the todo with the given id from the database.
		if (existingTodo != null) {
			todoRepository.delete(existingTodo); // delete the todo from the database
		} else {
			throw new NoSuchElementException("Todo not found"); // return null if the todo is not found
		}
	}
}
