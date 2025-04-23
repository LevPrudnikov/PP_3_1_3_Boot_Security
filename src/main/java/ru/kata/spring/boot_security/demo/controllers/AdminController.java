package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String displayAllUser(Model model){
        model.addAttribute("users",userService.getUsers());
        return "admin/index";
    }

    @GetMapping("/show")
    public String showUser(@RequestParam("id") Long id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("allRoles", roleService.getRoles());
        return "admin/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam("role_id") Long role_id,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/new";
        }
        user.setRole(roleService.getRole(role_id));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getRoles());
        return "admin/edit";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("user") @Valid User user,
                         @RequestParam("role_id") Long role_id,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/edit";
        }
        user.setRole(roleService.getRole(role_id));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String delete (@RequestParam("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
