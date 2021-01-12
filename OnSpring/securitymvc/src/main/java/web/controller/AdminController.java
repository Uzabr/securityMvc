package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(ModelMap map) {
        map.addAttribute("users", userService.listUser());
        map.addAttribute("roles", userService.getRole());
        map.addAttribute("newuser", new User());

        return "admin";
    }

    @PostMapping("/newuser")
    public String newUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String editPage(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String loadUserForEdit(ModelMap map, @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        map.addAttribute("loadUser",user);
        map.addAttribute("loadRoles", userService.getRole());
        return "updateUser";
    }

    @PostMapping("/delete/{id}")
    public String delete( @PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
