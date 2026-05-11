package me.gyeryeongban.readinglog.controller;

import me.gyeryeongban.readinglog.domain.Book;
import me.gyeryeongban.readinglog.domain.BookStatus;
import me.gyeryeongban.readinglog.dto.BookResponseDto;
import me.gyeryeongban.readinglog.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getBooks(
            @RequestParam(required = false) BookStatus status, Pageable pageable) {
        return ResponseEntity.ok(bookService.getBooks(status, pageable));
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
