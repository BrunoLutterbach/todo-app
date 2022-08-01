package br.com.brunolutterbach.todolist.DTO.form;

import br.com.brunolutterbach.todolist.model.ToDoItem;
import br.com.brunolutterbach.todolist.repository.ToDoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateTodoForm {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    public ToDoItem update(String id, ToDoRepository repository) {
        ToDoItem toDoItem = repository.findById(id).get();
        toDoItem.setTitle(title);
        toDoItem.setDescription(description);
        return toDoItem;
    }

}
