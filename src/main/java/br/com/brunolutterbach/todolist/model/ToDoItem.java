package br.com.brunolutterbach.todolist.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Getter
@Setter
@Document(collection = "ToDoList")
public class ToDoItem {

    @Id
    private String id;
    private String title;
    private String description;
    private LocalDate creationDate = LocalDate.now();
    private boolean done = false;


    public ToDoItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ToDoItem() {
    }
}
