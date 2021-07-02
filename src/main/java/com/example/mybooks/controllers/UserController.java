package com.example.mybooks.controllers;

import com.example.mybooks.model.Book;
import com.example.mybooks.model.User;
import com.example.mybooks.service.UserBookService;
import com.example.mybooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> newUser(@RequestBody User user)
    {
         userService.save(user);
         return new ResponseEntity<>("8080/users/" + user.getId(), HttpStatus.CREATED);
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
}
