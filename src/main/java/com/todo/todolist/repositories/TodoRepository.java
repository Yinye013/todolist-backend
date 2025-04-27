package com.todo.todolist.repositories;

import com.todo.todolist.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // This interface extends JpaRepository, which provides CRUD operations for the Todo entity.
    // The first parameter is the entity type (Todo), and the second parameter is the type of the entity's ID (Long).
    // No additional methods are needed here, as JpaRepository already provides methods for common operations.
}