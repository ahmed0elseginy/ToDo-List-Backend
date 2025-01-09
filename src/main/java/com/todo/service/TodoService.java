package com.todo.service;


import com.todo.model.Todo;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    private Long nextId = 1L;

    public Todo createTodo(Todo todoDto) {
//        Todo todo = new Todo();
//        todo.setId(nextId++);
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(false);
//        todo.setCreatedAt(LocalDateTime.now());
        Todo todo = new Todo(nextId++,todoDto.getTitle(),todoDto.getDescription(),false,LocalDateTime.now());
        todos.add(todo);
        return todo;
    }


    public List<Todo> getAllTodos() {
        return todos;
    }


    public Optional<Todo> getTodoById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodo = getTodoById(id);

        if (existingTodo.isPresent()) {
            Todo todoToUpdate = existingTodo.get();
            todoToUpdate.setTitle(updatedTodo.getTitle());
            todoToUpdate.setDescription(updatedTodo.getDescription());
            todoToUpdate.setCompleted(updatedTodo.isCompleted());
            return todoToUpdate;
        }
        return null;
    }

    public boolean deleteTodo(Long id) {
        return todos.removeIf(todo -> todo.getId().equals(id));
    }
}