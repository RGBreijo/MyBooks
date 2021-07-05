package com.example.mybooks.controllers;

import com.example.mybooks.model.Book;
import com.example.mybooks.model.User;
import com.example.mybooks.service.UserBookService;
import com.example.mybooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    UserService userService;
    UserBookService userBookService;

    @Autowired
    public UserController(UserService userService, UserBookService userBookService)
    {
        this.userService = userService;
        this.userBookService = userBookService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers()
    {
        return userService.findAll();
    }

    @PostMapping("/users")
    public void newUser(@RequestBody User user)
    {
         userService.save(user);
    }

    @GetMapping("/users/{id}")
    public Optional<User> findUser(@PathVariable Integer id)
    {
        return userService.findById(id);
    }

    @PostMapping("/users/{id}/books")
    public void findUser(@PathVariable Integer id, @RequestBody Book book)
    {
        userBookService.userBook(id, book);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }

    @Transactional
    @DeleteMapping("users/{id}/{book}")
    public void deleteBook(@PathVariable Integer id, @PathVariable String book){
        userBookService.deleteByTitle(id, book);
        //userService.findById(id).map(Book -> userBookService.deleteByTitle(id, book))
    }
}
