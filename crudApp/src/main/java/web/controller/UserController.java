package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Users;
import web.service.UserService;
import web.service.UserServiceImpl;

@Controller
public class UserController {


    private UserService userService;

    @Autowired
    public void setUserService (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(ModelMap map){
        map.addAttribute("users", new Users());
        map.addAttribute("userslist", userService.listUsers());
           return "users";
    }

    @PostMapping(value = "/add")
    public String addUsers(@ModelAttribute("users") Users users) {
        userService.addUsers(users);
        return "redirect:/";

    }

    @GetMapping("/new")
    public String newUsers(ModelMap map) {
        Users users = new Users();
        map.addAttribute("users", users);
        return "addUsers";
    }


    @PostMapping("/update")
    public String showUpdateUsers( @ModelAttribute("users") Users users){
        userService.updateUsers(users);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateUsers(ModelMap map, @PathVariable("id") long id){
        map.addAttribute("users", userService.getUsersById(id));
        return "updateUsers";
    }


    @GetMapping("/remove/{id}")
    public String removeUsers(@PathVariable("id") long id){
        userService.removeUsers(id);
        return "redirect:/";
    }

}
