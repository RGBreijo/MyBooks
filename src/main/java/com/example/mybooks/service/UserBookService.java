package com.example.mybooks.service;

import com.example.mybooks.model.Book;
import com.example.mybooks.model.User;
import com.example.mybooks.repository.BookRepository;
import com.example.mybooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService
{
    BookRepository bookRepository;
    UserRepository userRepository;

    @Autowired
    public UserBookService(BookRepository postRepository, UserRepository userRepository)
    {
        this.bookRepository = postRepository;
        this.userRepository = userRepository;
    }


    public void userBook(Integer userId, Book book)
    {
        if(userRepository.findById(userId).isPresent())
        {
            if(book.getDescription() == null || book.getDescription().equals(""))
            {
                book.setDescription(new OpenLibService().bookDescription(book.getTitle()));
            }

            if(book.getAuthor() == null || book.getAuthor().equals(""))
            {
                book.setAuthor(new OpenLibService().bookAuthors(book.getTitle()));
            }

            if(book.getBookCoverLink() == null || book.getBookCoverLink().equals(""))
            {
                book.setBookCoverLink(new OpenLibService().bookCover(book.getTitle()));
            }



            User user = userRepository.findById(userId).get();
            user.getBooks().add(book);
            book.setUser(user);

            userRepository.save(user);
            bookRepository.save(book);
        }
    }
}
