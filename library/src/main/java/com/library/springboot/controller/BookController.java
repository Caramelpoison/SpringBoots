package com.library.springboot.controller;


import com.library.springboot.model.Author;
import com.library.springboot.repository.AuthorRepository;
import com.library.springboot.model.Book;
import com.library.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());

        return "book/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") Book book) {
        Author author = book.getAuthor();
        if (author != null && author.getId() == null) {
            // Save the author first
            author = authorRepository.save(author);
            book.setAuthor(author);
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "book/update";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        book.setTitle(updatedBook.getTitle());
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        bookRepository.delete(book);
        return "redirect:/books";
    }
}