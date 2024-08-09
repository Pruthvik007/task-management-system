package com.app.taskmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private User user;

    public Task(String title, String description, TaskStatus status, TaskPriority priority, LocalDateTime dueDate, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.user = user;
    }

    public Task updateTask(Long id, String title, String description, TaskStatus status, TaskPriority priority, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;

        return this;
    }

    public enum TaskStatus {
        TODO, IN_PROGRESS, DONE
    }

    public enum TaskPriority {
        HIGH, MEDIUM, LOW
    }
}

//    private final String createdAt;
//    private final String updatedAt;

