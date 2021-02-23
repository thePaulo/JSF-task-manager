package paulov.tasksystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import paulov.tasksystem.model.Task;
import paulov.tasksystem.service.TaskService;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WebMockTest {


    @Mock
    private TaskService taskService;

    @Test
    public void mockObjectReturnedOnGetTask() {
        Task tsk = new Task(3L);
        
        taskService.addNewTask(tsk);
        Mockito.when(taskService.getTask(3L)).thenReturn(Optional.of(tsk));

        assertThat(taskService.getTask(3L).get().getId()).isEqualTo(tsk.getId());
    }
}