package br.com.brunolutterbach.todolist.controller;

import br.com.brunolutterbach.todolist.DTO.form.ToDoForm;
import br.com.brunolutterbach.todolist.DTO.form.UpdateTodoForm;
import br.com.brunolutterbach.todolist.model.ToDoItem;
import br.com.brunolutterbach.todolist.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/todo")
public class ToDoController {

    public final ToDoRepository toDoRepository;

    // Buscar por id
    @GetMapping("/{id}")
    public Optional<ToDoItem> findById(@PathVariable String id) {
        return toDoRepository.findById(id);
    }

    // Listar todos os itens
    @GetMapping()
    @Cacheable("todo")
    public List<ToDoItem> getAll() {
        return toDoRepository.findAll();
    }

    // Criar um novo item
    @CacheEvict(value = "todo", allEntries = true)
    @PostMapping()
    public ResponseEntity<ToDoForm> create(@RequestBody @Valid ToDoForm ToDoForm, UriComponentsBuilder uriBuilder) {
        ToDoItem toDoItem = ToDoForm.converter(new ToDoItem());
        toDoRepository.save(toDoItem);
        return ResponseEntity.created(uriBuilder.path("/todo/{id}").buildAndExpand(toDoItem.getId()).toUri()).body(ToDoForm);
    }

    // Atualizar um item
    @PutMapping("/{id}")
    public ResponseEntity<UpdateTodoForm> update(@PathVariable String id, @RequestBody @Valid UpdateTodoForm UpdateTodoForm) {
        ToDoItem toDoItem = UpdateTodoForm.update(id, toDoRepository);
        toDoRepository.save(toDoItem);
        return ResponseEntity.ok(UpdateTodoForm);
    }

    // Alterar done para true ou false
    @PatchMapping("/{id}")
    @CacheEvict(value = "todo", allEntries = true)
    public ResponseEntity<ToDoItem> isDone(@PathVariable String id) {
        Optional<ToDoItem> toDoItem = toDoRepository.findById(id);
        if (toDoItem.isPresent()) {
            toDoItem.get().setDone(!toDoItem.get().isDone());
            toDoRepository.save(toDoItem.get());
            return ResponseEntity.ok(toDoItem.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Deletar um item
    @DeleteMapping("/{id}")
    @CacheEvict(value = "todo", allEntries = true)
    public ResponseEntity<ToDoItem> delete(@PathVariable String id) {
        Optional<ToDoItem> toDoItem = toDoRepository.findById(id);
        if (toDoItem.isPresent()) {
            toDoRepository.delete(toDoItem.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


