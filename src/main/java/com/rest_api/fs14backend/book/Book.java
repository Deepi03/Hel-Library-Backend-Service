package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.genre.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="books")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(nullable = false,columnDefinition = "varchar(50)")
    private String title;
    @Column(nullable = false,columnDefinition = "varchar(50)")
    private String isbn;
    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    @ToString.Exclude
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genreId", nullable = false)
    @ToString.Exclude
    private Genre genre;
    @Column(nullable = false)
    private Date publishedDate;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false)
    private String cover;
   @Column(nullable = false,columnDefinition = "varchar(2000)")
   private String description;

    public Book(String title, String isbn, Author author, Genre genre, Date publishedDate, String publisher,
                String cover,String description) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.cover = cover;
        this.description = description;
    }
}
