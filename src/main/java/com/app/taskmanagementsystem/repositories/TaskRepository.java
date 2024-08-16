package com.app.taskmanagementsystem.repositories;

import com.app.taskmanagementsystem.entities.Task;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  @Query(
      "SELECT t FROM Task t WHERE t.user.id = :userId AND (:searchTerm IS NULL OR :searchTerm = ''"
          + " OR t.title LIKE CONCAT('%', :searchTerm, '%') OR t.description LIKE CONCAT('%',"
          + " :searchTerm, '%')) AND (:status IS NULL OR t.status = :status) AND (:priority IS NULL"
          + " OR t.priority = :priority) AND (:dueDateFrom IS NULL OR t.dueDate >= :dueDateFrom)"
          + " AND (:dueDateUntil IS NULL OR t.dueDate <= :dueDateUntil)")
  Page<Task> findTasksBySearchTermOrStatusOrPriorityOrderByDueDate(
      @Param("userId") Long userId,
      @Param("searchTerm") String searchTerm,
      @Param("status") Task.TaskStatus status,
      @Param("priority") Task.TaskPriority priority,
      @Param("dueDateFrom") LocalDateTime dueDateFrom,
      @Param("dueDateUntil") LocalDateTime dueDateUntil,
      Pageable pageable);
}
