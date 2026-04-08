package me.gyeryeongban.readinglog.repository;

import me.gyeryeongban.readinglog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
