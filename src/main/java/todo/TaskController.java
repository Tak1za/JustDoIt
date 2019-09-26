package todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/v1/{username}/tasks")
    List<Task> allTasks(@PathVariable String username){
        return repository.findAll();
    }

    @GetMapping("/api/v1/{username}/tasks/{id}")
    Task getTaskById(@PathVariable String username, @PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DeleteMapping("/api/v1/{username}/tasks/{id}")
    ResponseEntity<?> deleteTask(@PathVariable String username, @PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/v1/{username}/tasks/{id}")
    ResponseEntity<?> updateTask(@RequestBody Task newTask, @PathVariable String username, @PathVariable Long id){
        Task updatedTask = repository.findById(id).map(task->{
            task.setTitle(newTask.getTitle());
            task.setDescription(newTask.getDescription());
            return repository.save(task);
        }).orElseGet(()->{
            newTask.setId(id);
            return repository.save(newTask);
        });

        return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
    }

    @PostMapping("/api/v1/{username}/tasks")
    ResponseEntity<?> createTask(@RequestBody Task newTask, @PathVariable String username){
        Task createdTask = repository.save(newTask);

        return new ResponseEntity<Task>(createdTask, HttpStatus.CREATED);
    }
}
