package me.gyeryeongban.readinglog.service;

import me.gyeryeongban.readinglog.domain.Book;
import me.gyeryeongban.readinglog.domain.BookStatus;
import me.gyeryeongban.readinglog.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다."));
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book update(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));
        book.update(bookDetails.getTitle(), bookDetails.getAuthor(), bookDetails.getPublisher(), bookDetails.getStatus());

        return book;
    }

    public List<Book> getBooksByStatus(BookStatus status) {
        return bookRepository.findByStatus(status);
    }
}
