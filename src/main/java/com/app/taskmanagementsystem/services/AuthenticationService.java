package com.app.taskmanagementsystem.services;

import com.app.taskmanagementsystem.dtos.UserRegisterDto;
import com.app.taskmanagementsystem.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    User registerCustomer(UserRegisterDto userRegisterDto);
}
