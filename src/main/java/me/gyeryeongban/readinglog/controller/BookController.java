package me.gyeryeongban.readinglog.controller;

import me.gyeryeongban.readinglog.domain.Book;
import me.gyeryeongban.readinglog.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.update(id, bookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
