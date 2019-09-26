package todo;

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

    @DeleteMapping("/api/v1/{username}/tasks/{id}")
    ResponseEntity<?> deleteTask(@PathVariable String username, @PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
