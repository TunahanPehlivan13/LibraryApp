package com.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

@Repository
public interface IBookRepository extends MongoRepository<Book, String> {
}
