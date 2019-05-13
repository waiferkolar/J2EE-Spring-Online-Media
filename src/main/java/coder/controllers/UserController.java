package coder.controllers;

import coder.models.Authority;
import coder.models.User;
import coder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/admin/user/toggle/{id}")
    public String userById(@PathVariable("id") String id, Model model) {
        User user = userService.getUserById(Integer.parseInt(id));
        if (user.isEnabled()) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
        userService.updateUser(user);
        return "redirect:/admin/user/all";
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
    public String login(@RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("error", "Login Error, Please type again!");
        }

        if (logout != null) {
            model.addAttribute("logout", "Logout Successfully!");
        }

        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println(user);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
            return "register";
        } else {
            userService.addUser(user);
            userService.addAuth(new Authority(user.getUsername(), "ROLE_USER"));
            model.addAttribute("register_success", "Register Successful! Please Login!");
            return "login";
        }


    }


}
