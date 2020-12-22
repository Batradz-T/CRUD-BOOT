package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.sevice.UserDetailsServiceImpl;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    private UserDetailsServiceImpl userService;

//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "show";
//    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        //model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user")  User user) {
        userService.save(user);
        return "redirect:admin/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:admin/";
    }

    @RequestMapping(value = "admin/", method = RequestMethod.GET)
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "user/", method = RequestMethod.GET)
    public String getHomePage() {
        return "user";
    }
}
