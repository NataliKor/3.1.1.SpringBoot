package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAllBy();
    }

    @Override
    @Transactional
    public void saveOrUpdateUser(User user) {
        User userFromBD = userRepository.findById(user.getId());
        if (userFromBD == null) {
            System.out.println("Новый пользователь сохранен в БД.");
        } else {
            System.out.println("Пользователь обновлен в БД.");
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User searchUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User searchForUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
