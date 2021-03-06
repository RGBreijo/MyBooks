package com.example.mybooks.controllers;

import com.example.mybooks.exceptions.UserNotFoundException;
import com.example.mybooks.model.Book;
import com.example.mybooks.model.User;
import com.example.mybooks.service.UserBookService;
import com.example.mybooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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
    public User findUser(@PathVariable Integer id)
    {
        return userService.findById(id).orElseThrow(() -> new UserNotFoundException("id - " + id));
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<String> addBook(@PathVariable Integer id, @RequestBody Book book)
    {
        userBookService.userBook(id, book);
        return new ResponseEntity<>("8080/users/" + id, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userBookService.deleteAllbyId(id);
    }

    @Transactional
    @DeleteMapping("users/{id}/{book}")
    public void deleteBook(@PathVariable Integer id, @PathVariable String book){
        userBookService.deleteByTitle(id, book);
    }
}
