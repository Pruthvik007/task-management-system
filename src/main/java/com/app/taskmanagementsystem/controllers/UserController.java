package com.app.taskmanagementsystem.controllers;

import com.app.taskmanagementsystem.dtos.TaskDto;
import com.app.taskmanagementsystem.entities.Task;
import com.app.taskmanagementsystem.exceptions.TaskException;
import com.app.taskmanagementsystem.pojos.PageResponse;
import com.app.taskmanagementsystem.pojos.Response;
import com.app.taskmanagementsystem.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public Response<Task> createTask(@RequestBody @Valid TaskDto taskDto) {
        return Response.<Task>builder().data(userService.createTask(taskDto)).status(Response.Status.SUCCESS).build();
    }

    @GetMapping("/")
    public PageResponse<List<Task>> getTasks(@RequestParam(required = false) String searchTerm, @RequestParam(required = false) Task.TaskStatus status, @RequestParam(required = false) Task.TaskPriority priority, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDateFrom, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDateUntil, @RequestParam(required = false, defaultValue = "0") int pageNo) {
        Page<Task> pageOfTasks = userService.getTasks(searchTerm, status, priority, dueDateFrom, dueDateUntil, pageNo);
        return PageResponse.<List<Task>>builder().data(pageOfTasks.getContent()).totalPages(pageOfTasks.getTotalPages()).pageNo(pageNo).status(Response.Status.SUCCESS).build();
    }

    @PutMapping("/{taskId}")
    public Response<Task> updateTask(@PathVariable Long taskId, @RequestBody @Valid TaskDto taskDto) throws TaskException {
        return Response.<Task>builder().data(userService.updateTask(taskId, taskDto)).status(Response.Status.SUCCESS).build();
    }

    @DeleteMapping("/{taskId}")
    public Response<Task> deleteTask(@PathVariable Long taskId) throws TaskException {
        return Response.<Task>builder().data(userService.deleteTask(taskId)).status(Response.Status.SUCCESS).build();
    }

}

