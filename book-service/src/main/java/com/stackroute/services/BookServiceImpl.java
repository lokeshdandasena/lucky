package com.stackroute.services;

import com.stackroute.exception.BookNotFound;
import com.stackroute.model.Book;
import com.stackroute.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book uploadBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findBook(String bookTitle) {
        return bookRepository.findFirstByBookTitle(bookTitle);
    }

    @Override
    public String getBookPath(String bookTitle) throws BookNotFound {
        // String bookgenere[] = {"Novel", "Fiction", "Mystery", "Graphic novel"};
        // byte[] filebyte = {};
        // String bookdescription = "The Sign of the Four is the second of Arthur Conan
        // Doyle's Sherlock Holmes novels. In it the detective and his companion Dr
        // Watson unravel a mystery of hidden treasure and murder. ... By the end of the
        // story the criminals are either dead or arrested, and Miss Mary Morstan and
        // Watson are engaged to be married.";
        // Book book = new Book("The Sign of the Four", "Arthur Conan Doyle", "English",
        // bookgenere, bookdescription, "The-Sign-of-the-Four.pdf", filebyte,
        // "/assets/The-Sign-of-the-Four.pdf");
        // bookRepository.deleteAll();
        // bookRepository.save(book);
        Book book1 = (Book) bookRepository.findFirstByBookTitle(bookTitle);
        String url = book1.getBookUrl();
        // System.out.println("hello"+url);
        return url;
    }

    @Override
    public Book getBookDetails(String bookTitle) throws BookNotFound {

        Book book1 = (Book) bookRepository.findFirstByBookTitle(bookTitle);
        String url = book1.getBookUrl();
        return book1;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

}