package me.gyeryeongban.readinglog.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Book() {}

    public Book(String title, String author, String publisher, BookStatus status) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
    }
}
