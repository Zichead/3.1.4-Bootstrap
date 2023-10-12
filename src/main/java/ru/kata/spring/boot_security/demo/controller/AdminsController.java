package ru.kata.spring.boot_security.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final RoleService roleService;

    private final UserService userService;

    private static final String REDIRECT = "redirect:/admin";
    public AdminsController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("")
    public String allUsers ( @ModelAttribute("newUser") User newUser, Principal principal, Model model) {
        User admin = userService.findByUsername(principal.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("user", userService.getUserList());
        model.addAttribute("roles", roleService.allRoles());
        return "admin";
    }

    @PostMapping("")
    public String create(@ModelAttribute("user")User user) {
        userService.add(user);
        return REDIRECT;
    }



    @PatchMapping( "/{id}/edit")
    public String update(@ModelAttribute("user") User user) {

        userService.updateUser(user);
        return REDIRECT;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return REDIRECT;
    }
}
