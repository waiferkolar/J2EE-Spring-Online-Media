package coder.controllers;

import coder.models.Category;
import coder.models.Post;
import coder.models.User;
import coder.services.CatService;
import coder.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private CatService catService;
    @Autowired
    private PostService postService;

    @RequestMapping("/post/all")
    public String allPost(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "author.post.all";
    }

    @RequestMapping("/post/{id}")
    public String singlePost(@PathVariable("id") String id, Model model) {
        Post post = postService.getPostById(Integer.parseInt(id));
        List<Category> cats = catService.getAllCat();
        model.addAttribute("post", post);
        model.addAttribute("cats", cats);
        return "post";
    }


    @RequestMapping("/post/edit/{id}")
    public String showPostEditPage(@PathVariable("id") String id, Model model) {
        Post post = postService.getPostById(Integer.parseInt(id));
        List<Category> cats = catService.getAllCat();
        model.addAttribute("post", post);
        model.addAttribute("cats", cats);
        return "author.post.edit";
    }

    @RequestMapping("/post/edit")
    public String editPost(@ModelAttribute("post") Post post, Model model, HttpServletRequest request) {
        MultipartFile file = post.getFile();

        if (file != null && !file.isEmpty()) {
            String imageName = saveImage(file, request);
            post.setImage(imageName);
        } else {
            System.out.println("File Not Exist");
        }

        postService.updatePost(post);

        return "redirect:/post/all";
    }

    @RequestMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") String id, Model model) {
        postService.deletePost(Integer.parseInt(id));
        return "redirect:/post/all";
    }


    @RequestMapping("/post/create")
    public String showPostCreate(Model model) {
        List<Category> cats = catService.getAllCat();
        model.addAttribute("cats", cats);
        model.addAttribute("post", new Post());
        return "author.post.create";
    }

    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute("post") Post post, Model model, HttpServletRequest request) {

        MultipartFile file = post.getFile();
        // Get Sessoin User and set to Post's User

        if (file != null && !file.isEmpty()) {
            String imageName = saveImage(file, request);
            post.setImage(imageName);
        }
        post.setUser_id(1);
        postService.addPost(post);

        return "redirect:/post/all";
    }

    public String saveImage(MultipartFile file, HttpServletRequest request) {

        String imageName = System.currentTimeMillis() + ".png";

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Path path = Paths.get(rootDirectory + "/WEB-INF/assets/imgs/" + imageName);

        if (file != null && !file.isEmpty()) {
            try {
                file.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Image Can't be upload!");
            }
        }

        return imageName;

    }

}
