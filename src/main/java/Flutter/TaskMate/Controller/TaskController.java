package Flutter.TaskMate.Controller;

import Flutter.TaskMate.Entity.TaskEntity;
import Flutter.TaskMate.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Support both /api/tasks/{userId} and /api/tasks?userId=123
    @GetMapping
    public List<TaskEntity> getTasksByQuery(@RequestParam(name = "userId", required = false) Long userId) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId is required");
        }
        return taskService.getTasks(userId);
    }

    @GetMapping("/{userId}")
    public List<TaskEntity> getTasks(@PathVariable Long userId) {
        return taskService.getTasks(userId);
    }

    // Create task when the client provides userId in the JSON body
    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity task, @RequestParam(name = "userId", required = false) Long userId) {
        if (task.getUserId() == null && userId != null) {
            task.setUserId(userId);
        }
        if (task.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId is required");
        }
        return taskService.createTask(task);
    }

    // Convenience endpoint to create task with userId in the path
    @PostMapping("/{userId}")
    public TaskEntity createTaskForUser(@PathVariable Long userId, @RequestBody TaskEntity task) {
        task.setUserId(userId);
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskEntity updateTask(@PathVariable Long id, @RequestBody TaskEntity task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/toggle/{id}")
    public TaskEntity toggleTask(@PathVariable Long id) {
        return taskService.toggleTask(id);
    }
}
