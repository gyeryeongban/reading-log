package me.gyeryeongban.readinglog.repository;

import me.gyeryeongban.readinglog.domain.Book;
import me.gyeryeongban.readinglog.domain.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(BookStatus status);
    Page<Book> findByStatus(BookStatus status, Pageable pageable);
}
