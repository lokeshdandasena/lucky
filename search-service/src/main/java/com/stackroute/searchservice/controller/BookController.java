package com.stackroute.searchservice.controller;

import com.stackroute.searchservice.model.Book;
import com.stackroute.searchservice.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/books")
public class BookController {

    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @PostMapping
    public Book insertBook(@RequestBody Book book) throws Exception{
        return bookRepository.insertBook(book);
    }
    @GetMapping("/allbooks")
    public Map<String , Object> getAllBook() {
        return bookRepository.getAllBook();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getBookById(@PathVariable String id){
        return bookRepository.getBookById(id);
    }

    @GetMapping("/all")
    public Map<String, Object> getAllBooks(){
        return bookRepository.getAllBook();
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id){
        return bookRepository.updateBookById(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable String id){
        bookRepository.deleteBookById(id);
    }


    @GetMapping("/search/{queries}")
    public List<Book> BookSearch(@PathVariable String queries){
        System.out.println("\n\n\nInside Book Service.Ths is what we have got from book query :  "+queries);
        String text = "";
//        for (String query: queries) {
//            text = text.concat(query);
//            text = text.concat(" ");
//        }
        System.out.println("\n\nReturnning back to bookquery service");
        return bookRepository.findBook(queries);
    }
}
