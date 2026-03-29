package com.br.marcus.saborfy.domain.user.controller;


import com.br.marcus.saborfy.domain.user.dto.response.RegisterUserResponse;
import com.br.marcus.saborfy.domain.user.dto.request.RegisterUserRequest;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.domain.user.service.CreateUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        User newUser = createUserService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getName(), newUser.getRegistration()));
    }
}
