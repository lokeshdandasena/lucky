package com.stackroute.repository;

import com.stackroute.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    public Book findFirstByBookTitle(String bookTitle);
}
