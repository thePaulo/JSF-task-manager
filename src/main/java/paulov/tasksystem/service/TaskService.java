package paulov.tasksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import paulov.tasksystem.dao.TaskDao;
import paulov.tasksystem.model.Task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public Optional<Task> getTask(Long id){
        return taskDao.findById(id);
    }
    
    public List<Task> getTasks() { return taskDao.findAll();}
    
    public void addNewTask(Task task){
        task.setCreation(LocalDateTime.now());
        task.setStatus("Em andamento");
        
        if (task.getTitle().equals("")){
            task.setTitle("Tarefa padrão");
        }

        if (task.getOwner().equals("")){
            task.setTitle("Responsável padrão");
        }
        
        if( task.getDeadline() == null){
            task.setDeadline(60L);
        }
        
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

        LocalDateTime now = LocalDateTime.now();
        Date d1 = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date d2 = Date.from(task.getCreation().atZone(ZoneId.systemDefault()).toInstant());

        long dif = (d1.getTime()-d2.getTime())/1000;

        System.out.println(dif + "Esta eh a diferença");

        System.out.println( dif<task.getDeadline());
        
        if( dif < task.getDeadline() ) { // if the updated task is under the deadline then update it
            if (_task.getTitle() != null) {
                task.setTitle(_task.getTitle());
            }
            if (_task.getDescription() != null) {
                task.setDescription(_task.getDescription());
            }
            if (_task.getOwner() != null) {
                task.setOwner(_task.getOwner());
            }
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Erro - Não é possível atualizar esta tarefa devido ao deadline expirado");
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
