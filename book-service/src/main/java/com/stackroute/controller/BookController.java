package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.exception.BookNotFound;
import com.stackroute.model.Book;
import com.stackroute.services.BookService;
import com.stackroute.services.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("api/v1/book")
public class BookController {

    private BookService bookService;
    private RabbitMqSender rabbitMqSender;

    @Autowired
    public BookController(BookService bookService, RabbitMqSender rabbitMqSender) {
        this.bookService = bookService;
        this.rabbitMqSender = rabbitMqSender;
    }

    @Value("${app.message}")
    private String message;

    @PostMapping("/upload")
    public ResponseEntity<Book> Uploadbook(@RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "image") MultipartFile image, @RequestParam("item") String item) throws IOException {

        Book bookk = new ObjectMapper().readValue(item, Book.class);
        bookk.setBookId(UUID.randomUUID().toString());
        bookk.setFileByte(file.getBytes());
        bookk.setFile(file.getOriginalFilename());
        bookk.setImagebyte(image.getBytes());
        bookk.setImage(image.getOriginalFilename());
        bookk.setType(image.getContentType());
        bookk.setUploadedOn(new Date(System.currentTimeMillis()));

        /*
         * String filedirectory="H:\\"; file.transferTo(new File(filedirectory +
         * file.getOriginalFilename()));
         * 
         * 
         * String imagedirectory="H:\\";
         */

        String filedirectory = "C:\\Users\\DELL\\Desktop\\subjects\\";
        file.transferTo(new File(filedirectory + file.getOriginalFilename()));

        String imagedirectory = "C:\\Users\\DELL\\Desktop\\subjects\\";

        image.transferTo(new File(filedirectory + file.getOriginalFilename()));
        Book uploadbook = bookService.uploadBook(bookk);
        rabbitMqSender.send(uploadbook);
        return new ResponseEntity<>(uploadbook, HttpStatus.CREATED);
    }

    @GetMapping("/bookpath/{bookTitle}")
    public ResponseEntity<String> getBookById(@PathVariable String bookTitle) throws BookNotFound {
        // bookTitle = "The Sign of the Four";
        String bookDetails = bookService.getBookPath(bookTitle);
        return new ResponseEntity<String>(bookDetails, HttpStatus.OK);
    }

    @GetMapping("/bookdetails/{bookTitle}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String bookTitle) throws BookNotFound {
        Book bookDetails = bookService.getBookDetails(bookTitle);
        return new ResponseEntity<Book>(bookDetails, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
