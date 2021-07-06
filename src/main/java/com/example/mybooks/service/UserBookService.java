package com.example.mybooks.service;

import com.example.mybooks.model.Book;
import com.example.mybooks.model.User;
import com.example.mybooks.repository.BookRepository;
import com.example.mybooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            // Even though our front end doesn't allow the user to enter these fields we're leaving the checks
            // for any body using the API that may want the added functionality
//            if(book.getDescription() == null || book.getDescription().equals(""))
//            {
//                book.setDescription(new OpenLibService().bookDescription(book.getTitle()));
//            }
//
//            if(book.getAuthor() == null || book.getAuthor().equals(""))
//            {
//                book.setAuthor(new OpenLibService().bookAuthors(book.getTitle()));
//            }
//
//            if(book.getBookCoverLink() == null || book.getBookCoverLink().equals(""))
//            {
//                book.setBookCoverLink(new OpenLibService().bookCover(book.getTitle()));
//            }

            String[] bookInfo = new OpenLibService().callApi(book.getTitle());

            book.setDescription(bookInfo[0]);
            book.setAuthor(bookInfo[1]);
            book.setBookCoverLink(bookInfo[2]);

            User user = userRepository.findById(userId).get();
            user.getBooks().add(book);
            book.setUser(user);

            userRepository.save(user);
            bookRepository.save(book);
        }
    }

    public void deleteByTitle(Integer userId, String title){
        if(userRepository.findById(userId).isPresent())
        {

            Integer bookId = 0;
            List<Book> books = userRepository.findById(userId).get().getBooks();
            for (Book b: books) {
                if(b.getTitle().equals(title)){
                    bookId = b.getId();
                }
            }
            bookRepository.deleteById(bookId);

        }
    }

    public void deleteAllbyId(Integer id){
        if(userRepository.findById(id).isPresent()) {
            List<Book> books = userRepository.findById(id).get().getBooks();
            for (Book b : books) {
                bookRepository.deleteById(b.getId());
            }
        }

        userRepository.deleteById(id);
    }

}
