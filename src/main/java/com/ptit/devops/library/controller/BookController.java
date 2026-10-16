package com.ptit.devops.library.controller;

import com.ptit.devops.library.entity.Book;
import com.ptit.devops.library.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    /** GET /api/books - danh sách sách tg thư viện */
//    @GetMapping("/books")
//    public List<Book> findAll() {
//        return repository.findAll(Sort.by("id"));
//    }
    @GetMapping("/books")
    public Map<String, Object> submission() {
        Map<String, String> env = System.getenv();
        return Map.of(
                "student", Map.of(
                        "name", env.getOrDefault("STUDENT_NAME", ""),
                        "id", env.getOrDefault("STUDENT_ID", ""),
                        "class", env.getOrDefault("STUDENT_CLASS", ""),
                        "email", env.getOrDefault("STUDENT_EMAIL", ""),
                        "github", env.getOrDefault("GITHUB_USERNAME", "")
                ),
                "version", env.getOrDefault("APP_VERSION", "unknown"),
                "books", repository.findAll(Sort.by("id"))
        );
    }


}
