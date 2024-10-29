package com.example.CategoryCRUDApp.controller;


import com.example.CategoryCRUDApp.model.Category;
import com.example.CategoryCRUDApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
    private CategoryRepository categoryRepository;

    // all category
    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/list";
    }

    // Form add category new
    @GetMapping("/new")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    // add category new
    @PostMapping
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    // Form repair category ID
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categories/form";
        } else {
            return "redirect:/categories";
        }
    }

    // update category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") Category updatedCategory) {
        updatedCategory.setId(id);
        categoryRepository.save(updatedCategory);
        return "redirect:/categories";
    }

    // delete category ID
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
