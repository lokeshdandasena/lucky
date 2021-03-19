package com.stackroute.recommendationservice.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

// POJO for Book Class
@Node
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@Id", scope = Book.class)
@Data
public class Book {

    @Id
    private String bookId;
    private String bookTitle;
    private String authorName;
    private String language;
    private String[] bookGenre;
    private String description;
}