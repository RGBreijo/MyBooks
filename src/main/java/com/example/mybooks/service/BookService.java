package com.example.mybooks.service;

import com.example.mybooks.model.Book;
import com.example.mybooks.model.User;
import com.example.mybooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService
{

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findById(Integer id)
    {
        return bookRepository.findById(id);
    }

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public void save(Book book)
    {
        bookRepository.save(book);
    }
}
