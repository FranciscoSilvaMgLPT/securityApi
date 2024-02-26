package com.example.security.controller;

import com.example.security.dto.UserDto;
import com.example.security.entity.User;
import com.example.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addUser(userDto));
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable final Long userId) {
        return service.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable final Long userId, @RequestBody UserDto userDto) {
        return service.updatePutUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable final Long userId) {
        service.deleteUser(userId);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUserDetail(@PathVariable final Long userId, @RequestBody UserDto userDto) {
        return service.updatePatchUser(userId, userDto);
    }

}
