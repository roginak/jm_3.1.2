package com.roginak.springboot.controllers;

import com.roginak.springboot.entities.User;
import com.roginak.springboot.service.RoleService;
import com.roginak.springboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByLogin(auth.getName()));

        return "user/info";
    }

    @RequestMapping(value = "admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/all";
    }

    @GetMapping("/admin/add")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin/add";
    }

    @PostMapping("/admin/add")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.editUser(user);

        return "redirect:/admin";
    }


}