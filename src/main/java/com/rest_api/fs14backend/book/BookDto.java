package com.rest_api.fs14backend.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class BookDto {
    private UUID genre;
    private UUID author;
    private String title;
    private String isbn;
    private Date publishedDate;
    private String publisher;
    private String cover;
    private String description;
    private boolean isAvailable;
}
