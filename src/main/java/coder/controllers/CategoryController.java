package coder.controllers;

import coder.models.Category;
import coder.models.Post;
import coder.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CatService catService;

    @RequestMapping("/cat/all")
    public String catAll(Model model) {
        List<Category> cats = catService.getAllCat();
        model.addAttribute("cats", cats);

        return "admin.cat.all";
    }

    @RequestMapping("/cat/{id}")
    public String catPage(@PathVariable("id") String id, Model model) {
        Category category = catService.getCatById(Integer.parseInt(id));
        List<Category> cats = catService.getAllCat();
        List<Post> posts = category.getPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("cats", cats);
        return "catpage";
    }

    @RequestMapping("/cat/edit/{id}")
    public String showEditCatPage(@PathVariable("id") String id, Model model) {
        Category category = catService.getCatById(Integer.parseInt(id));
        model.addAttribute("category", category);
        return "admin.cat.edit";
    }

    @RequestMapping(value = "/cat/edit", method = RequestMethod.POST)
    public String editCat(@ModelAttribute("category") Category category, Model model) {
        catService.updateCat(category);
        return "redirect:/cat/all";
    }

    @RequestMapping("/cat/delete/{id}")
    public String deleteCat(@PathVariable("id") String id, Model model) {
        catService.deleteCat(Integer.parseInt(id));
        return "redirect:/cat/all";
    }

    @RequestMapping("/cat/create")
    public String showCatCreatePage(Model model) {
        model.addAttribute("category", new Category());
        return "admin.cat.create";
    }


    @RequestMapping(value = "/cat/create", method = RequestMethod.POST)
    public String showCatCreatePage(@ModelAttribute("category") Category category, Model model) {
        catService.addCat(category);
        return "redirect:/cat/all";
    }

}
