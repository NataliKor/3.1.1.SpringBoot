package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //выводим всех users
    List<User> findAllBy();

    //поиск пользователя по id
    User findById(int id);

    //поиск пользователя по имени
    User findByUsername(String name);

    //удаляем user
    void deleteById(int id);
}
