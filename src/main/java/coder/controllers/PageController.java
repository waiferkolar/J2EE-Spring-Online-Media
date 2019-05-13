package coder.controllers;

import coder.models.Category;
import coder.models.Post;
import coder.models.User;
import coder.services.CatService;
import coder.services.PostService;
import coder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private PostService postService;
    @Autowired
    private CatService catService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        List<Category> cats = catService.getAllCat();
        model.addAttribute("posts", posts);
        model.addAttribute("cats", cats);
        return "home";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }


    @RequestMapping("/admin/home")
    public String admin(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "admin.home";
    }

    @RequestMapping("/user/home")
    public String userHome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean bol = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if (bol) {
            return "redirect:/admin/home";
        } else {
            return "author.home";
        }
    }

    @RequestMapping("/author/home")
    public String author(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userService.getUserByName(username);
        model.addAttribute("posts", user.getPosts());
        return "author.home";
    }

}
