package com.example.mybooks.service;


import com.example.mybooks.model.User;
import com.example.mybooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public void save(User user)
    {
        userRepository.save(user);
    }


    // delete user
    // update user
    // remove book

}
