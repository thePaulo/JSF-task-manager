package paulov.tasksystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import paulov.tasksystem.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskDao extends JpaRepository<Task,Long>{

    @Query("SELECT t FROM Task t WHERE t.status = ?1")
    List<Task> findTaskByStatus(String status);
}
