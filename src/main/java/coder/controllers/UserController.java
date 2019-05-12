package coder.controllers;

import coder.models.Post;
import coder.models.User;
import coder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public String userById(@PathVariable("id") String id, Model model) {
        User user = userService.getUserById(Integer.parseInt(id));
        System.out.println(user);
        return "";
    }

    @RequestMapping("/admin/user/all")
    public String userById(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "admin.user.all";
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        userService.deleteUser(Integer.parseInt(id));
        return "";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
