package com.rest_api.fs14backend.genre;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(nullable = false , columnDefinition = "varchar(50)")
    private String name;
    @Column(nullable = false)
    private String coverImage;
    @Column(nullable = false)
    private String description;
}
