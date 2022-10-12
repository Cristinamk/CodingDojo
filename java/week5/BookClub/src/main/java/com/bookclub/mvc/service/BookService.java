package com.bookclub.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.mvc.models.Book;
import com.bookclub.mvc.repository.BookRepository;


@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	
	
	public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
       return bookRepository.findById(id).orElse(null);	
	}
    
    //Update
    public Book updateBook(Book book) {
    	return bookRepository.save(book);
    }
    //Delete
    	 
    public void destroyBook(Long id) {
    	bookRepository.deleteById(id);
    }

}
