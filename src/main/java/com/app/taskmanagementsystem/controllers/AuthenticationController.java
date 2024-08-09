package com.app.taskmanagementsystem.controllers;

import com.app.taskmanagementsystem.dtos.UserLoginDto;
import com.app.taskmanagementsystem.dtos.UserRegisterDto;
import com.app.taskmanagementsystem.entities.User;
import com.app.taskmanagementsystem.helpers.JwtHelper;
import com.app.taskmanagementsystem.pojos.Response;
import com.app.taskmanagementsystem.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public Response<User> registerCustomer(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return Response.<User>builder().data(authenticationService.registerCustomer(userRegisterDto)).status(Response.Status.SUCCESS).build();
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
        );
        return Response.<String>builder().data(jwtHelper.generateToken(authentication.getName())).status(Response.Status.SUCCESS).build();
    }
}

