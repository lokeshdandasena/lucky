package com.stackroute.services;

import com.stackroute.exception.BookNotFound;
import com.stackroute.model.Book;


import java.util.List;

public interface BookService {
    Book uploadBook(Book book);

    Book findBook(String bookTitle);
    Book getBookDetails(String bookTitle) throws BookNotFound;
    String getBookPath(String bookTitle) throws BookNotFound;

    List<Book> getAllBooks();
}