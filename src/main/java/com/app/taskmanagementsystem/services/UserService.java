package com.app.taskmanagementsystem.services;

import com.app.taskmanagementsystem.dtos.TaskDto;
import com.app.taskmanagementsystem.entities.Task;
import com.app.taskmanagementsystem.exceptions.TaskException;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

public interface UserService {

  Page<Task> getTasks(
      String searchTerm,
      Task.TaskStatus status,
      Task.TaskPriority priority,
      LocalDateTime dueDateFrom,
      LocalDateTime dueDateUntil,
      int pageNo);

  Task createTask(TaskDto taskDto);

  Task updateTask(Long taskId, TaskDto taskDto) throws TaskException;

  Task deleteTask(Long taskId) throws TaskException;
}
