package paulov.tasksystem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Task {

    @Id
    @SequenceGenerator(
            name="topic_sequence",
            sequenceName = "topic_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "topic_sequence"
    )
    private Long id;
    private String title;
    private String description;
    private String owner;
    private String priority;
    private LocalDateTime deadline;

    public Task() {
    }

    public Task(Long id) {
        this.id = id;
    }

    public Task(String title, String description, String owner, String priority) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.priority = priority;
    }

    public Task(Long id, String title, String description, String owner, String priority, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.priority = priority;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
