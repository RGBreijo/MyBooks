package com.example.mybooks.service;


import com.example.mybooks.model.User;
import com.example.mybooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Integer id)
    {
        return userRepository.findById(id);
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public void createUser(User user)
    {
        userRepository.save(user);
    }


    // delete user
    // update user
    // remove book

}
