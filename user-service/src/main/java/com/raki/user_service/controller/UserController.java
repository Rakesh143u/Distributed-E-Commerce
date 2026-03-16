package com.raki.user_service.controller;


import com.raki.user_service.model.User;
import com.raki.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service=service;
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByid(@PathVariable Integer id){
        return service.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        return service.createUser(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        return service.deleteUserById(id);
    }
}
