package com.rest_api.fs14backend.transaction;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.user.User;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @ToString.Exclude
    private User user;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private boolean isReturned;
    private Date toBeReturned;

    public Transaction(User user, Book book, Date borrowDate, Date toBeReturned,boolean isReturned) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.toBeReturned = toBeReturned;
        this.isReturned  = isReturned;
    }

    public UUID getUser() {
        return user.getId();
    }
    public UUID getBook() {
        return book.getId();
    }
}
