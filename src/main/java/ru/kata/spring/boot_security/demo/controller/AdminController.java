package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    private String allUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "admin";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/edit")
    public String GetEditPage(@RequestParam(name="id",required = true) int id, Model model) {
        User user = userService.getById(id);
        List<Role> listRoles = (List<Role>) userService.allRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "editPage";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/edit")
    public String PostEditPage(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/delete")
    private String GetDelete(@RequestParam(name="id",required = true) int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/add")
    public String GetAdd(Model model) {
        User user = userService.create();
        List<Role> listRoles = (List<Role>) userService.allRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "addPage";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/add")
    public String PostAddPage(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }
}
