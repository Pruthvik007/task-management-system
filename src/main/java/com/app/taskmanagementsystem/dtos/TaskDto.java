package com.app.taskmanagementsystem.dtos;

import com.app.taskmanagementsystem.entities.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
public class TaskDto {
  @NotBlank(message = "Task Title is Required")
  private String title;

  @NotBlank(message = "Task Description is Required")
  private String description;

  @NotNull(message = "Task Status is Required")
  private Task.TaskStatus status;

  @NotNull(message = "Task Priority is Required")
  private Task.TaskPriority priority;

  @NotNull(message = "Task Due Date is Required")
  private LocalDateTime dueDate;
}
