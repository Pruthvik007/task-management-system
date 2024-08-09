package com.app.taskmanagementsystem.services.impl;

import com.app.taskmanagementsystem.dtos.TaskDto;
import com.app.taskmanagementsystem.entities.Task;
import com.app.taskmanagementsystem.exceptions.TaskException;
import com.app.taskmanagementsystem.helpers.AppConstants;
import com.app.taskmanagementsystem.helpers.SecurityHelper;
import com.app.taskmanagementsystem.repositories.TaskRepository;
import com.app.taskmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final TaskRepository taskRepository;

    @Override
    public Page<Task> getTasks(String searchTerm, Task.TaskStatus status, Task.TaskPriority priority, LocalDateTime dueDateFrom, LocalDateTime dueDateUntil, int pageNo) {
        Page<Task> tasks = taskRepository.findTasksBySearchTermOrStatusOrPriorityOrderByDueDate(SecurityHelper.getLoggedInUser().getId(), searchTerm, status, priority, dueDateFrom, dueDateUntil, Pageable.ofSize(AppConstants.PAGE_SIZE).withPage(pageNo));
        System.out.println(tasks);
        return tasks;
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        return taskRepository.save(new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getStatus(), taskDto.getPriority(), taskDto.getDueDate(), SecurityHelper.getLoggedInUser()));
    }

    @Override
    public Task updateTask(Long taskId, TaskDto taskDto) throws TaskException {
        Task task = getTask(taskId);
        return taskRepository.save(task.updateTask(taskId, taskDto.getTitle(), taskDto.getDescription(), taskDto.getStatus(), taskDto.getPriority(), taskDto.getDueDate()));
    }

    @Override
    public Task deleteTask(Long taskId) throws TaskException {
        Task task = getTask(taskId);
        taskRepository.delete(task);
        return task;
    }

    public Task getTask(Long taskId) throws TaskException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskException("Task not found"));
        if (!task.getUser().getId().equals(SecurityHelper.getLoggedInUser().getId())) {
            throw new TaskException("Task not found");
        }
        return task;
    }
}

