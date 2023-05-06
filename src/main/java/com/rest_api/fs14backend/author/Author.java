package com.rest_api.fs14backend.author;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="author")
@NoArgsConstructor
@Data
public class Author {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;
    @Column(nullable = false)
    private String info;
    public Author(String name, String info) {
        this.name = name;
        this.info = info;
    }
}
