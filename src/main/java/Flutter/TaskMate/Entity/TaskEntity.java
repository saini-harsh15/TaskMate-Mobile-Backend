package Flutter.TaskMate.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // ✅ Force mapping to correct column name & default
    @Column(name = "is_completed", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean completed = false;

    private LocalDateTime dueDate;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
