package ru.g_shamkhal.SpringBootEducation.service;

import ru.g_shamkhal.SpringBootEducation.dto.UserDto;
import ru.g_shamkhal.SpringBootEducation.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
