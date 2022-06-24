package com.huyvv20.springmongodb.repository;

import com.huyvv20.springmongodb.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findBookByAuthorContaining (String author);

    List<Book> findBookByPublishDateBetween(Date start, Date end);

    List<Book> findBookByBookNameContaining(String name);
}
