package com.todo.todolist.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todo.todolist.models.Todo;
import com.todo.todolist.models.TodoRequestDTO;
import com.todo.todolist.models.TodoResponseDTO;
import com.todo.todolist.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin; // 👈avoid that pesky cors error

@CrossOrigin(origins = "http://localhost:5173") // 👈so that it can allow requests from my frontend.
@RestController
@RequestMapping("/todos")

public class TodoController {
    // This class is a controller class that handles the HTTP requests and responses for the Todo application. It uses the TodoService class to perform the business logic and return the response to the client.
    // The controller is annotated with @RestController, which indicates that it is a RESTful controller and will return JSON responses.
    // The controller is also annotated with @RequestMapping("/api/todos"), which indicates that all the endpoints in this controller will be prefixed with /api/todos.
    // The controller uses the TodoService class to perform the business logic and return the response to the client. The TodoService class is injected

    @Autowired // This annotation indicates that this class will be injected with the TodoService class.
    private final TodoServices todoService;

    public TodoController(TodoServices todoService) {
        this.todoService = todoService; // inject the TodoService class into the controller
    }

    // GET TODOS
    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos(); // return the list of todos
    }

    // GET TODO BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id); // return the todo with the given id
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST TODO
    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@RequestBody TodoRequestDTO requestDTO) {
        Todo todo = new Todo(); // create a new todo with the given title and description
        todo.setTitle(requestDTO.getTitle());
        todo.setDescription(requestDTO.getDescription());
        todo.setCompleted(requestDTO.getCompleted()); // set the completed status to false
        
        Todo createdTodo = todoService.createTodo(todo); // use the service to create and save the todo

        TodoResponseDTO responseDTO = new TodoResponseDTO();
        responseDTO.setId(createdTodo.getId());
        responseDTO.setTitle(createdTodo.getTitle());
        responseDTO.setDescription(createdTodo.getDescription());
        responseDTO.setCompleted(createdTodo.isCompleted());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // UPDATE TODO (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id, todo); // update the todo with the given id
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK); // return the updated todo with a 200 status code
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // return a 404 status code if the todo is not found
        }
    }

    // DELETE TODO (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id); // delete the todo with the given id
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // return a 204 status code
    }
}