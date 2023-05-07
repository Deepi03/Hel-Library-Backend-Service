package com.rest_api.fs14backend.author;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="authors")
@NoArgsConstructor
@Data
public class Author {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;
    @Column(nullable = false, columnDefinition = "varchar(1000)")
    private String info;
    @Column(nullable = false)
    private String image;
    public Author(String name, String info,String image) {
        this.name = name;
        this.info = info;
        this.image=image;
    }
}
