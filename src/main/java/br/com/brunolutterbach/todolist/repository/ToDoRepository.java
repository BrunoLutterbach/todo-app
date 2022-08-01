package br.com.brunolutterbach.todolist.repository;

import br.com.brunolutterbach.todolist.model.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDoItem, String> {

}

