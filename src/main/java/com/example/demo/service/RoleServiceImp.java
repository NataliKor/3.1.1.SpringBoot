package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findByAuthority(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAllBy() {
        return roleRepository.findAllBy();
    }
}
