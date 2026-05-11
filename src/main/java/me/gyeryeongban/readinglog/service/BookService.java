package me.gyeryeongban.readinglog.service;

import lombok.RequiredArgsConstructor;
import me.gyeryeongban.readinglog.domain.Book;
import me.gyeryeongban.readinglog.domain.BookStatus;
import me.gyeryeongban.readinglog.dto.BookRequestDto;
import me.gyeryeongban.readinglog.dto.BookResponseDto;
import me.gyeryeongban.readinglog.dto.mapper.BookMapper;
import me.gyeryeongban.readinglog.exception.BookNotFoundException;
import me.gyeryeongban.readinglog.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        Book book = bookMapper.toEntity(bookRequestDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("해당 책이 없습니다. (ID: " + id + ")"));
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

    public Page<BookResponseDto> getBooks(BookStatus status, Pageable pageable) {
        Page<Book> page = (status == null)
                ? bookRepository.findAll(pageable)
                : bookRepository.findByStatus(status, pageable);  // 기존 findByStatus 활용
        return page.map(bookMapper::toDto);
    }
}
