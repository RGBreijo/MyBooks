package com.example.mybooks.repository;

import com.example.mybooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>
{
}
