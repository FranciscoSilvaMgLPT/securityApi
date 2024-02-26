package com.example.security.service;


import com.example.security.dto.UserDto;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.exceptions.InvalidRequestException;
import com.example.security.exceptions.NoPermissionException;
import com.example.security.exceptions.UserNotFoundException;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    private static final String ERROR_USER_NOT_FOUND = "User not found!";
    private static final String ERROR_NO_PERMISSION = "No permission!";

    public List<UserDto> getUsers() {
        return repository.findAll().stream().map(e -> UserDto.builder()
                .id(e.getId())
                .username(e.getUsername())
                .email(e.getEmail())
                .role(e.getRole()).build()).toList();
    }

    public UserDto addUser(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getPassword() == null || userDto.getEmail() == null) {
            throw new InvalidRequestException("Username, password and email cannot be empty");
        }
        repository.save(User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .role(userDto.getRole()).build());
        return userDto;
    }

    public UserDto getUserById(final Long userId) {
        Optional<User> userAux = repository.findById(userId);
        if (userAux.isEmpty()) throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        return UserDto.builder()
                .username(userAux.get().getUsername())
                .email(userAux.get().getEmail())
                .role(userAux.get().getRole())
                .build();
    }

    public UserDto updatePutUser(final Long userId, UserDto userDto) {
        Optional<User> userAux = repository.findById(userId);
        if (userAux.isEmpty()) {
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        }

        User userToUpdate = userAux.get();
        if (userDto.getUsername() == null || userDto.getPassword() == null || userDto.getEmail() == null) {
            throw new InvalidRequestException("Invalid body request. Username, Password and email cannot be empty!");
        }
        userToUpdate.setUsername(userDto.getUsername());
        userToUpdate.setPassword(userDto.getPassword());
        repository.save(userToUpdate);
        return userDto;
    }

    public void deleteUser(final Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        }
/*        if(ToFindOut.role == Role.USER){
            throw new NoPermissionException(ERROR_NO_PERMISSION);
        }
        if(ToFindOut.role == Role.ADMIN && user.get().getRole() == Role.ADMIN || Role.HEADMASTER){
            throw new NoPermissionException(ERROR_NO_PERMISSION);
        }*/
        repository.delete(user.get());
    }

    public UserDto updatePatchUser(final Long userId, UserDto userDto) {
        Optional<User> userAux = repository.findById(userId);
        if (userAux.isEmpty()) throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
        if (userDto.getUsername() == null && userDto.getPassword() == null && userDto.getEmail() == null)
            throw new InvalidRequestException("Invalid body request. Username, Password and Email cannot be empty!");
        User updatedUser = userAux.get();
        if (userDto.getUsername() != null) updatedUser.setUsername(userDto.getUsername());
        if (userDto.getPassword() != null) updatedUser.setPassword(userDto.getPassword());
        if (userDto.getEmail() != null) updatedUser.setEmail(userDto.getEmail());
        repository.save(updatedUser);
        return userDto;

    }
}
