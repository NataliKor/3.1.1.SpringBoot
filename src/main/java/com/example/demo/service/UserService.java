package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void saveOrUpdateUser(User user);

    User searchUser(int id);

    User searchForUserByName(String name);

    void deleteUserById(int id);
}
