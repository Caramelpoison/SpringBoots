package com.library.springboot.controller;

import com.library.springboot.model.Author;
import com.library.springboot.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/create";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + id));
        model.addAttribute("author", author);
        return "author/update";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute("author") Author updatedAuthor) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + id));
        author.setName(updatedAuthor.getName());
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + id));
        authorRepository.delete(author);
        return "redirect:/authors";
    }
}