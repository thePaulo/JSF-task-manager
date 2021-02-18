package paulov.tasksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulov.tasksystem.dao.TaskDao;
import paulov.tasksystem.model.Task;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getTasks() { return taskDao.findAll();}
    
    public void addNewTask(Task task){
        task.setDeadline(LocalDateTime.now());
        taskDao.save(task);
    }
    
    public void deleteTask(Long taskId){
        taskDao.deleteById(taskId);
    }
}
