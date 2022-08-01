package br.com.brunolutterbach.todolist.DTO.form;

import br.com.brunolutterbach.todolist.model.ToDoItem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ToDoForm {

    @NotNull @NotEmpty
    private String title;

    @NotNull @NotEmpty
    private String description;


    public ToDoItem converter(ToDoItem toDoItem) {
        return new ToDoItem(title, description);
    }
}
