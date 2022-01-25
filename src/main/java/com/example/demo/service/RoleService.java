package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {
    //поиск роли по id
    Role findById(int id);

    //поиск роли по имени
    Role findByAuthority(String name);

    //выводим все roles
    List<Role> findAllBy();
}
