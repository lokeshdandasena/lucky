package com.stackroute.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import lombok.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Book.class)
@Document(collection = "books")
// @Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {

    // @Id
    private String bookId;
    @NonNull
    private String bookTitle;
    @NonNull
    private String authorName;
    @NonNull
    private String language;
    @NonNull
    private String[] bookGenre;
    @NonNull
    private String description;
    @NonNull
    private String file;
    @NonNull
    private byte[] fileByte;
    @NonNull
    private String bookUrl;
    private String coverUrl;
    // @Temporal(TemporalType.DATE)
    private Date uploadedOn;
    private int readingTimeInMinutes;
    private int totalPages;
    private String image;
    private String type;
    private byte[] imagebyte;

    public Book(@NonNull String bookTitle, @NonNull String authorName, @NonNull String language,
            @NonNull String[] bookGenre, @NonNull String description, @NonNull String file, @NonNull String bookUrl,
            String coverUrl, int readingTimeInMinutes, int totalPages) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.language = language;
        this.bookGenre = bookGenre;
        this.description = description;
        this.file = file;
        this.bookUrl = bookUrl;
        this.coverUrl = coverUrl;
        this.readingTimeInMinutes = readingTimeInMinutes;
        this.totalPages = totalPages;
    }
}