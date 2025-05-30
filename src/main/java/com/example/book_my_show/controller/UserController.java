package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.book_my_show.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
    // This controller will handle user-related operations
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }
}
