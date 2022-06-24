package com.huyvv20.springmongodb.controller;
import com.huyvv20.springmongodb.exception.DuplicateRecordException;
import com.huyvv20.springmongodb.exception.ResourceNotFoundException;
import com.huyvv20.springmongodb.model.Book;
import com.huyvv20.springmongodb.repository.BookRepository;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        List<Book> checkBook = this.bookRepository.findAll();
        if(book != null){
            for(int i=0; i<checkBook.size(); i++){
                if(book.getBookName().equals(checkBook.get(i).getBookName())){
                    throw new DuplicateRecordException("Book's name is already exit!!!");
                }
            }
        } else {
            throw new NullPointerException("Book is null!!!");
        }
        bookRepository.save(book);
        return "Added book with id: " + book.get_id();
    }

    @GetMapping("/findBook/{id}")
    public Optional<Book> getBook(@PathVariable String id){
        return bookRepository.findById(id);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity< Book > updateBook(@PathVariable String id,
                                             @RequestBody Book bookDetails) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

        book.setBookName(bookDetails.getBookName());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublishDate(bookDetails.getPublishDate());
        book.setDescription(bookDetails.getDescription());
        final Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable String id){
        bookRepository.deleteById(id);
        return "Book deleted with id: " + id;
    }

    @GetMapping("/searchBookByAuthor/{text}")
    public List<Book> findBookByAuthorContaining(@PathVariable String text) {
        return bookRepository.findBookByAuthorContaining(text);
    }

    @GetMapping("/searchBookByName/{name}")
    public List<Book> findByBookNameContaining(@PathVariable String name) {
        return bookRepository.findBookByBookNameContaining(name);
    }

    @GetMapping("/searchBookByPublishDate")
    public ResponseEntity<List<Book>> getBookBetweenPublishDate(@RequestParam(value = "start")   @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                         @RequestParam(value = "end")   @DateTimeFormat(pattern = "yyyy-MM-dd") Date end){
        return new ResponseEntity<List<Book>>(bookRepository.findBookByPublishDateBetween(start, end), HttpStatus.OK);
    }

    //Use http://localhost:8080/findAllBooks/?page=... to test this service
    @GetMapping("/findAllBooks")
    List<Book> findAllBooks(@PageableDefault(sort = "publishDate", size = 30, direction = Sort.Direction.DESC) Pageable pageable){
        return bookRepository.findAll(pageable).getContent();
    }

}
