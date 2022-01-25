package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // открытие страницы авторизации
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // обработчик выхода Spring Security
    @PostMapping("/logout")
    public String logout() {
        return "login";
    }

    // открытие страницы приветствия авторизованного пользователя
    @GetMapping("/hello")
    public String printWelcome(Model model) {
        User user = userService.searchForUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "user/user";
    }

    //вывод всех польхователь из БД в представление
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/index";
    }

    //передаем пустого пользователя на форму
    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        user.setRoles(new HashSet<>(roleService.findAllBy()));
        model.addAttribute("user", user);
        return "admin/new";
    }

    //добавляем пользователя, переданного с формы, в БД
    @PostMapping()
    public String add(@ModelAttribute("user") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/";
    }

    //возвращает в форму пользователя по id
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.searchUser(id));
        return "admin/edit";
    }

    //принимаем из формы нового пользователя и обновляем его по id в БД
    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") int id) {
        userService.saveOrUpdateUser(user);
        return "redirect:/";
    }

    //удаляем из БД пользователя по id
    @RequestMapping(value = "/delete/{id}", produces = "application/json", method = {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
