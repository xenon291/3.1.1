package com311.Spring_demo1.controller;

import com311.Spring_demo1.model.User;
import com311.Spring_demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/users")
@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "all-users";
    }

    @GetMapping( "/add")
    public String showAddUserForm(Model modal) {
        modal.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("user") @Validated User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        userService.save(user);
        return "redirect:/users";

    }

    @GetMapping("/edit")
    public String showEditUser (@RequestParam("id") int id, Model model) {
        User userById = userService.getUserById(id);
        if (userById != null) {
            model.addAttribute("user", userById);
            return "edit-user";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") @Validated User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String showDeleteUser(@RequestParam("id") int id, Model model) {
        User userById = userService.getUserById(id);
        if (userById != null) {
            model.addAttribute("user", userById);
            return "delete-user";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {

        userService.delete(id);

        return "redirect:/users";
    }
}
