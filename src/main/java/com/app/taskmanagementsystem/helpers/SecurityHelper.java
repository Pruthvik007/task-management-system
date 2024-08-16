package com.app.taskmanagementsystem.helpers;

import com.app.taskmanagementsystem.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {
  private SecurityHelper() {}

  public static User getLoggedInUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
