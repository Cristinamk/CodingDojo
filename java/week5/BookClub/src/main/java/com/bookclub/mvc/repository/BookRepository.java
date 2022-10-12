package com.bookclub.mvc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookclub.mvc.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
	List<Book> findAll();

}
