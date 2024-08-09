package com.app.taskmanagementsystem.services.impl;

import com.app.taskmanagementsystem.dtos.UserRegisterDto;
import com.app.taskmanagementsystem.entities.User;
import com.app.taskmanagementsystem.repositories.UserRepository;
import com.app.taskmanagementsystem.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public User registerCustomer(UserRegisterDto userRegisterDto) {
        User user = new User(userRegisterDto.getName(), userRegisterDto.getEmail(), passwordEncoder.encode(userRegisterDto.getPassword()), User.UserStatus.ACTIVE, userRegisterDto.getUserRole());
        return userRepository.save(user);
    }
}

