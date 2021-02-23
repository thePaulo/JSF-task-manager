package paulov.tasksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
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
        task.setStatus("Em andamento");
        taskDao.save(task);
    }
    
    public void deleteTask(Long taskId){
        taskDao.deleteById(taskId);
    }
    
    //Long taskId, String title, String description, String owner
    @Transactional
    public void updateTask(Task _task){
        Task task = taskDao.findById(_task.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Tarefa com id: "+ _task.getId()+ " não encontrada"));
        
        if(_task.getTitle() != null){
            task.setTitle(_task.getTitle());
        }
        if(_task.getDescription() != null){
            task.setDescription(_task.getDescription());   
        }
        if(_task.getOwner() != null){
            task.setOwner(_task.getOwner());
        }
    }
    
    public List<Task> getOngoingTasks(){
        return taskDao.findTaskByStatus("Em andamento");
    }
    
    public void setTaskConcludedStatus(Task task){
        task.setStatus("concluido");
        taskDao.save(task);
    }
}
