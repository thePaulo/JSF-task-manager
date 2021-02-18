package paulov.tasksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paulov.tasksystem.model.Task;
import paulov.tasksystem.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {return taskService.getTasks();}
    
    @PostMapping
    public List<Task> registerTask(@RequestParam(required = false) String title,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String owner,
                             @RequestParam(required = false) String priority) {
        Task task = new Task(title,description,owner,priority);
        taskService.addNewTask(task);
        
        return getTasks();
    }
    
    @DeleteMapping(path="{Id}")
    public void deleteTask(@PathVariable("Id") Long taskId){ taskService.deleteTask(taskId); }

}
