package coder.controllers;

import coder.models.Category;
import coder.models.Post;
import coder.services.CatService;
import coder.services.PostService;
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
    public String admin() {
        return "admin.home";
    }

    @RequestMapping("/author/home")
    public String author() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            System.out.println(username);
        } else {
            String username = principal.toString();
            System.out.println(username);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean bol = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

//        if (bol) {
//            return "redirect:/admin/home";
//        } else {
//            return "author.home";
//        }
        return "author.home";
    }

}
